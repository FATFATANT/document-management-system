package top.catoy.docmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByName(@Param("userName") String userName);

    List<User> getAllUsers();

    int deleteUserById(int id);

    User selectUserById(@Param("userId") int userId);

    int insertUser(User user);


    int updateUser(User user);

    int getIsLockedByUserName(@Param("name") String name);
}
