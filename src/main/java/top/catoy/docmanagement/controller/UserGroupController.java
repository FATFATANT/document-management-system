package top.catoy.docmanagement.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.domain.UserGroup;
import top.catoy.docmanagement.service.UserGroupService;

import java.security.Security;
import java.util.List;

@RestController
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping("/addUserGroup")
    public ResponseBean addUserGroup(@RequestBody UserGroup userGroup){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("管理员")){
            System.out.println(userGroup);
            int result = userGroupService.addUserGroup(userGroup);
            if(result > 0 ){
                return new ResponseBean(ResponseBean.SUCCESS,"创建成功",null);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"创建失败",null);
            }
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"你没有权限",null);
        }
    }


    @RequestMapping(value = "/getAllUserGroup",method = RequestMethod.POST)
    public ResponseBean getAllUserGroup(){
        List<UserGroup> userGroups = userGroupService.getAllUserGroup();
        if(userGroups != null){
            return new ResponseBean(ResponseBean.SUCCESS,"查找成功!",userGroups);
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"查找失败!",null);
        }
    }


    @RequestMapping(value = "/deleteUserGroup",method = RequestMethod.POST)
    public ResponseBean deleteUserGroup(@RequestBody UserGroup userGroup){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("管理员")){
            int result = userGroupService.deleteUserGroup(userGroup);
            if(result > 0 ){
                return new ResponseBean(ResponseBean.SUCCESS,"删除成功",null);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"删除失败",null);
            }
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"你没有相关权限",null);
        }
    }



    @RequestMapping(value = "/updateUserGroup",method = RequestMethod.POST)
    public ResponseBean updateUserGroup(@RequestBody UserGroup userGroup){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("管理员")){
            System.out.println(userGroup);
            int result = userGroupService.updatePermission(userGroup);
            if(result > 0 ){
                return new ResponseBean(ResponseBean.SUCCESS,"修改成功",null);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"修改失败",null);
            }
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"你没有此操作权限",null);
        }
    }


}
