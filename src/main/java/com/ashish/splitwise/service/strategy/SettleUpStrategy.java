package com.ashish.splitwise.service.strategy;

import com.ashish.splitwise.model.Expense;
import com.ashish.splitwise.model.SettlementTransaction;

import java.util.List;

public interface SettleUpStrategy {

    List<SettlementTransaction> getSettlementTransactions(int groupId);
}
