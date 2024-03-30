package com.ashish.splitwise.controller;

import com.ashish.splitwise.model.SettlementTransaction;
import com.ashish.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/settle/{groupId}")
    public ResponseEntity  doSettlement(@PathVariable("groupId") int groupId){
        List<SettlementTransaction> settle =  groupService.settleUp(groupId);
        return ResponseEntity.ok(settle);
    }

}
