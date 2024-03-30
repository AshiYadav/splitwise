package com.ashish.splitwise.mapper;

import com.ashish.splitwise.dto.UserFriendResponseDTO;
import com.ashish.splitwise.dto.UserGroupResponseDTO;
import com.ashish.splitwise.dto.UserSignupReponseDTO;
import com.ashish.splitwise.model.Group;
import com.ashish.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class EntityToDTO {

    public static UserSignupReponseDTO UserEtityToSignupResponse(User user){
        UserSignupReponseDTO dto = new UserSignupReponseDTO();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setId(user.getId());

        List<UserFriendResponseDTO> friendDTOList = new ArrayList<>();
        if(user.getFriendsList() != null) {
            for (User fueser : user.getFriendsList()) {
                friendDTOList.add(getRequiredInfo(fueser));
            }
        }
        dto.setFriendsList(friendDTOList);

        List<UserGroupResponseDTO> groupDTOList = new ArrayList<>();
        if(user.getGroupList() != null) {
            for (Group grp : user.getGroupList()) {
                groupDTOList.add(getRequiredGroupInfo(grp));
            }
        }
        dto.setGroupList(groupDTOList);


        return dto;
    }

    public static UserFriendResponseDTO getRequiredInfo(User user){
        UserFriendResponseDTO dto = new UserFriendResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public static UserGroupResponseDTO getRequiredGroupInfo(Group grp){
        UserGroupResponseDTO dto = new UserGroupResponseDTO();
        dto.setId(grp.getId());
        dto.setName(grp.getName());
        return dto;
    }

}
