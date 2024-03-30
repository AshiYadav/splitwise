package com.ashish.splitwise.model;

import com.ashish.splitwise.model.constant.GroupType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "SPLITWISE_GROUP")
public class Group extends BaseModel{

    private String name;
    private LocalDateTime creationDate;
    private double totalAmountSpent;
    @ManyToMany
    private List<User> members;
    @OneToMany
    private List<Expense> expenseList;
    @ManyToOne
    private User groupCreatedBy;
    @OneToMany
    private List<SettlementTransaction> settlementTransactions;



}
