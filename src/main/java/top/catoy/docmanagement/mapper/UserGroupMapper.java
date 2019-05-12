package top.catoy.docmanagement.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.User;
import top.catoy.docmanagement.domain.UserGroup;

@Mapper
public interface UserGroupMapper {

    public UserGroup getUserGroupById(@Param("id") int id);

    public int getUserGroupName(@Param("name") String name);

}
