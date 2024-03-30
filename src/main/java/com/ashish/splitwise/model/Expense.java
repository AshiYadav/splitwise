package com.ashish.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Getter
@Setter
@Entity(name = "SPLITWISE_EXPENSE")
public class Expense extends BaseModel{

    private String description;
    private Currency currency;
    private double amount;
    private LocalDateTime expenseTime;
    @ManyToOne
    private User addedBy;
    @OneToMany
    private List<UserExpense> userExpenseList;


}
