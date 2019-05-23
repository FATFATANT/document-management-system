package top.catoy.docmanagement.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.User;
import top.catoy.docmanagement.domain.UserGroup;

import java.util.List;

@Mapper
public interface UserGroupMapper {

    public UserGroup getUserGroupById(@Param("id") int id);

    public int getUserGroupName(@Param("name") String name);

    public int addUserGroup(UserGroup userGroup);

    public int deleteUserGroup(UserGroup userGroup);

    public List<UserGroup> getAllUserGroup();

    public int updateUserGroup(UserGroup userGroup);


}
