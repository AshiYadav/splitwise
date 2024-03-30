package com.ashish.splitwise.service.strategy;

import com.ashish.splitwise.dto.UserAmount;
import com.ashish.splitwise.model.Expense;
import com.ashish.splitwise.model.SettlementTransaction;
import com.ashish.splitwise.model.User;
import com.ashish.splitwise.model.UserExpense;
import com.ashish.splitwise.model.constant.UserExpenseType;
import com.ashish.splitwise.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class MinimumTransactionSettleUpStrategy implements SettleUpStrategy{

    @Autowired
    GroupRepository groupRepository;


    @Override
    public List<SettlementTransaction> getSettlementTransactions(int groupId) {
        List<Expense> expenses = groupRepository.findById(groupId).get().getExpenseList();
        HashMap<User,Double> map = getOutstadingBalance(expenses);
        Comparator<UserAmount> len = Comparator.comparingDouble(UserAmount ::getAmount);
        Comparator<UserAmount> bor = Comparator.comparingDouble(UserAmount ::getAmount).reversed();
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>(len);
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>(bor);

        for(Map.Entry<User,Double> mp : map.entrySet()){

            User user = mp.getKey();
            Double amount = mp.getValue();

            if(amount < 0){
                minHeap.add(new UserAmount(user,amount));
            }
            else if(amount > 0){
                maxHeap.add(new UserAmount(user,amount));
            }
            else{
                System.out.println("user does not need to participate in settle up");
            }
        }

        List<SettlementTransaction> tra = new ArrayList<>();

        while (!maxHeap.isEmpty() && !minHeap.isEmpty()){
            UserAmount lender = maxHeap.poll();
            UserAmount borrower = minHeap.poll();

            if(Math.abs(borrower.getAmount()) < lender.getAmount()){
                //Lendor = 1000, Borrower = -500
                lender.setAmount(lender.getAmount() + borrower.getAmount());
                maxHeap.add(lender);
                SettlementTransaction tran = new SettlementTransaction(borrower.getUser(),lender.getUser(),Math.abs(borrower.getAmount()));
                tra.add(tran);
            }
            else if(Math.abs(borrower.getAmount()) < lender.getAmount()){
                //Lendor = 500, Borrower = -1000 , borrower pays lendor 500
                borrower.setAmount(borrower.getAmount() + lender.getAmount());
                minHeap.add(borrower);
                SettlementTransaction tran = new SettlementTransaction(borrower.getUser(),lender.getUser(),lender.getAmount());
                tra.add(tran);
            }
            else{
                //Lendor = 500, Borrower = -500
                //Transaction -> Borrower to Lendor 500, and both are free from settle up
                System.out.println("Do nothing, both are equal");
                SettlementTransaction tran = new SettlementTransaction(borrower.getUser(),lender.getUser(),lender.getAmount());
                tra.add(tran);
            }
        }

        return  tra;
    }

    public HashMap<User,Double> getOutstadingBalance(List<Expense> expenses){
        HashMap<User,Double> map = new HashMap<User, Double>();

        for(Expense exp : expenses){
            for(UserExpense ue : exp.getUserExpenseList()){
                User user = ue.getUser();
                Double amount = ue.getAmount();

                if(map.containsKey(user)){
                    if(ue.getUserType().equals(UserExpenseType.PAID)){
                        map.put(user,map.get(user) + amount);
                    }
                    else{
                        map.put(user,map.get(user) - amount);
                    }
                }
                else{
                    if(ue.getUserType().equals(UserExpenseType.PAID)){
                        map.put(user,0 + amount);
                    }
                    else{
                        map.put(user,0 - amount);
                    }
                }
            }
        }

        return map;

    }
}

/*
       1. Go through all the expenses, and find the total outstanding for each person
       2. All borrowers will go to a min heap
       3. All lendors will go to a max heap
       4. pull min from minHeap and max from maxHeap, and create a transaction
       5. Update the balances, put them back in heap
       6. Keep doing until heap is empty.


       Lendor = 500, Borrower = -500
       make both of them zero, and keep both of them out of the heap

       Lendor = 1000, Borrower = -500
       borrower will become zero, lendor will become 500 and lendor will go inside the heap again

       Lendor = 500, Borrower = -1000
       Lendor will become zero, Borrower will become -500 and Borrower will go inside the heap again
 */
