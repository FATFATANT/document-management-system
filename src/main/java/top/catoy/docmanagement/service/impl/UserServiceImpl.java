package top.catoy.docmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.domain.User;
import top.catoy.docmanagement.domain.UserGroup;
import top.catoy.docmanagement.domain.UsertableInfo;
import top.catoy.docmanagement.mapper.DepartmentMapper;
import top.catoy.docmanagement.mapper.UserGroupMapper;
import top.catoy.docmanagement.mapper.UserMapper;
import top.catoy.docmanagement.service.LogService;
import top.catoy.docmanagement.service.UserGroupService;
import top.catoy.docmanagement.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-26 16:14
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private LogService logService;


    @Override
    public User getUserByName(String userName) {
        try {
            System.out.println(userMapper.getUserByName(userName));
            User user = userMapper.getUserByName(userName);
            return userMapper.getUserByName(userName);
        }catch (RuntimeException r){
            r.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseBean getAllUsers(int page) {
        PageHelper.startPage(page, 10);
        List<User> users = userMapper.getAllUsers();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        List<UsertableInfo> list = new ArrayList<>();
        for(int i = 0 ;i < users.size();i++){
            User user = users.get(i);
            UsertableInfo usertableInfo = new UsertableInfo();
            usertableInfo.setUserId(user.getUserId());
            usertableInfo.setUserName(user.getUserName());
            usertableInfo.setPassword(user.getUserPassword());
            usertableInfo.setRealname("高副帅");
            String departmentName = departmentMapper.getDepartmentNameById(user.getDepartmentId());
            UserGroup userGroup = userGroupMapper.getUserGroupById(user.getGroupId());
            usertableInfo.setDepartment(departmentName);
            usertableInfo.setRole(userGroup.getGroupName());
            usertableInfo.setPermission(userGroup.getGroupPermission());
            if(user.getUserLock() == 0){
                usertableInfo.setIslocked("未锁定");
            }else {
                usertableInfo.setIslocked("已锁定");
            }
            list.add(usertableInfo);
        }
        PageInfo<UsertableInfo> pageInfo = new PageInfo<>(list);
//        pageInfo.setPageNum(userPageInfo.getPageNum());
        pageInfo.setTotal(userPageInfo.getTotal());
//        System.out.println(pageInfo);
        if(users!=null && users.size()>0){
            return new ResponseBean(ResponseBean.SUCCESS,"查询成功",pageInfo);
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
        }
    }

    @Override
    public ResponseBean Login(String username, String password) {
        User user = userMapper.getUserByName(username);
        if(user.getUserLock() == 1){
            return new ResponseBean(ResponseBean.FAILURE,"用户已锁定",null);
        }else {
            if(user != null){
                if(user.getUserPassword().equals(password)){
                    UserGroup userGroup = userGroupMapper.getUserGroupById(user.getGroupId());
                    User userInfo = new User();
                    userInfo.setUserId(user.getUserId());
                    userInfo.setDepartmentId(user.getDepartmentId());
                    userInfo.setUserName(user.getUserName());
                    userInfo.setUserPassword(user.getUserPassword());
                    userInfo.setUserLock(user.getUserLock());
                    userInfo.setRole(userGroup.getGroupName());
                    System.out.println(userGroup.getGroupName());
                    userInfo.setPermission(userGroup.getGroupPermission());
                    logService.insertLog(user.getUserId(),"用户登入","用户管理");
                    return new ResponseBean(ResponseBean.SUCCESS,"登录成功",userInfo);
                }else {
                    return new ResponseBean(ResponseBean.FAILURE,"密码错误",null);
                }
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"用户不存在",null);
            }
        }
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateUser(user);
    }
}
