package com.ashish.splitwise.service;

import com.ashish.splitwise.model.SettlementTransaction;
import com.ashish.splitwise.service.strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    SettleUpStrategy settleUpStrategy;

    @Override
    public List<SettlementTransaction> settleUp(int groupId) {
        List<SettlementTransaction> settle = settleUpStrategy.getSettlementTransactions(groupId);
        return settle;
    }
}
