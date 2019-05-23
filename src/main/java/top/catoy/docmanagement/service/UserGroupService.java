package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.UserGroup;

import java.util.List;

public interface UserGroupService {

    public int updatePermission(UserGroup userGroup);

    int getUserGroupIdByName(String name);

    int addUserGroup(UserGroup userGroup);

    List<UserGroup> getAllUserGroup();

    int deleteUserGroup(UserGroup userGroup);
}

