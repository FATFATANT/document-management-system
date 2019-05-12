package top.catoy.docmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.UserGroup;
import top.catoy.docmanagement.mapper.UserGroupMapper;
import top.catoy.docmanagement.service.UserGroupService;


@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupMapper userGroupMapper;

    public UserGroup getUserGroupByUserId(int id){
        return userGroupMapper.getUserGroupById(id);
    }

    @Override
    public int updatePermission() {
        return 0;
    }

    @Override
    public int getUserGroupIdByName(String name) {
        return userGroupMapper.getUserGroupName(name);
    }
}
