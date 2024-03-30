package com.ashish.splitwise.service;

import com.ashish.splitwise.model.SettlementTransaction;

import java.util.List;

public interface GroupService {

    public List<SettlementTransaction> settleUp(int groupId);
}
