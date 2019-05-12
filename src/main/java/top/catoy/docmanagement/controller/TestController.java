package top.catoy.docmanagement.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.docmanagement.domain.ResponseBean;

@RestController
public class TestController {
    @RequestMapping(value = "/testapi",method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ResponseBean TestApi(String user){
        return new ResponseBean(ResponseBean.SUCCESS,"成功",null);
    }
}
