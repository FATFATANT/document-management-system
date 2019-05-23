package top.catoy.docmanagement.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.docmanagement.domain.ResponseBean;

import javax.security.auth.Subject;

@RestController
public class TestController {
    @RequestMapping(value = "/testapi",method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ResponseBean TestApi(String user){
        return new ResponseBean(ResponseBean.SUCCESS,"成功",null);
    }


    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    public void TestPermission(){
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.hasRole("管理员"));

    }
}

