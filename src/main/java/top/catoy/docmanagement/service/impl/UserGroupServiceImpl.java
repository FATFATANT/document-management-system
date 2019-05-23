package top.catoy.docmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.UserGroup;
import top.catoy.docmanagement.mapper.UserGroupMapper;
import top.catoy.docmanagement.service.UserGroupService;

import java.util.List;


@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupMapper userGroupMapper;

    public UserGroup getUserGroupByUserId(int id){
        return userGroupMapper.getUserGroupById(id);
    }

    @Override
    public int updatePermission(UserGroup userGroup) {
        return userGroupMapper.updateUserGroup(userGroup);
    }

    @Override
    public int getUserGroupIdByName(String name) {
        return userGroupMapper.getUserGroupName(name);
    }

    @Override
    public int addUserGroup(UserGroup userGroup) {
        return userGroupMapper.addUserGroup(userGroup);
    }

    @Override
    public List<UserGroup> getAllUserGroup() {
        return userGroupMapper.getAllUserGroup();
    }

    @Override
    public int deleteUserGroup(UserGroup userGroup) {
        return userGroupMapper.deleteUserGroup(userGroup);
    }
}
