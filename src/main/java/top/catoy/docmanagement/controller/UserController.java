package top.catoy.docmanagement.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.catoy.docmanagement.domain.LogSearchParams;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.domain.User;
import top.catoy.docmanagement.domain.UsertableInfo;
import top.catoy.docmanagement.service.DepartmentService;
import top.catoy.docmanagement.service.LogService;
import top.catoy.docmanagement.service.UserGroupService;
import top.catoy.docmanagement.service.UserService;
import top.catoy.docmanagement.utils.FileDownLoadUtil;
import top.catoy.docmanagement.utils.JWTUtil;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private LogService logService;


    @Value("${jwt.secret}")
    private String serct;

    @PostMapping("/public/login")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password")String password) {

        ResponseBean result = userService.Login(username,password);
        System.out.println(result);
        if(result.getMsg().equals("用户已锁定")){
            return new ResponseBean(ResponseBean.NOT_LEGAL,"用户已锁定",null);
        }
        if(result.getMsg().equals("密码错误")){
            return new ResponseBean(ResponseBean.NOT_LEGAL,"密码错误",null);
        }
        if(result.getMsg().equals("登录成功")){
                String token = JWTUtil.sign((User) result.getData(),serct);
                return new ResponseBean(ResponseBean.SUCCESS, "登陆成功", JWTUtil.sign((User) result.getData(), serct));
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"登录失败",null);
            }

    }

    @PostMapping("/public/getAllUsers")
    public ResponseBean getAllUsers(@Param("page") int page){
        ResponseBean result = userService.getAllUsers(page);
        if(result.getMsg().equals("查询成功")) {
            return new ResponseBean(ResponseBean.SUCCESS,"查询成功",result.getData());
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
        }
    }


    @PostMapping("/public/deleteUser")
//    @RequiresRoles("admin")
    public ResponseBean deleteUserById(@Param("userId") int userId) throws UnauthenticatedException {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            int result = userService.deleteUserById(userId);
            if(result > 0 ){
                return new ResponseBean(ResponseBean.SUCCESS,"删除成功",null);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"删除失败",null);
            }
        }else {
            return new ResponseBean(ResponseBean.ERROR,"你无权限进行此操作",null);
        }
    }

    @PostMapping("/public/createUser")
    public ResponseBean createUser(@RequestBody UsertableInfo usertableInfo){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            System.out.println(usertableInfo+"-----------------------------------------------------------");
            User user = new User();
            user.setUserName(usertableInfo.getUserName());
            user.setUserPassword(usertableInfo.getPassword());
            user.setUserLock(0);
            int departmentid = departmentService.getDepartmentIdByName(usertableInfo.getDepartment());
            int roleId = userGroupService.getUserGroupIdByName(usertableInfo.getRole());
            user.setGroupId(roleId);
            user.setDepartmentId(departmentid);
            int result = userService.insertUser(user);
            if(result > 0 ){
                return new ResponseBean(ResponseBean.SUCCESS,"创建成功",null);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"创建失败",null);
            }
        }else {
            return new ResponseBean(ResponseBean.ERROR,"你无权限进行此操作",null);
        }
    }


    @PostMapping("/public/updateUserMessage")
    public ResponseBean updateUserMessage(@RequestBody UsertableInfo usertableInfo){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            System.out.println(usertableInfo);
            User user = new User();
            user.setUserId(usertableInfo.getUserId());
            user.setUserName(usertableInfo.getUserName());
            user.setUserPassword(usertableInfo.getPassword());
            int departmentid = departmentService.getDepartmentIdByName(usertableInfo.getDepartment());
            int roleid = userGroupService.getUserGroupIdByName(usertableInfo.getRole());
            user.setGroupId(roleid);
            user.setDepartmentId(departmentid);
            System.out.println("---------------------====================="+usertableInfo.getIslocked());
            if(usertableInfo.getIslocked().equals("false")){
                user.setUserLock(0);
            }else if(usertableInfo.getIslocked().equals("true")){
                user.setUserLock(1);
            }
            int result = userService.update(user);
            if(result > 0){
                return new ResponseBean(ResponseBean.SUCCESS,"修改成功",null);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"修改失败",null);
            }
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"你没有权限进行此操作",null);
        }

    }

    @GetMapping("/admin/getAllLogs")
    public ResponseBean getAllLogs(@RequestParam String currentPage,
                                   @RequestParam String pageSize){
        ResponseBean result = logService.getAllLogs(Integer.parseInt(currentPage),Integer.parseInt(pageSize));
        return result;
    }
//    @GetMapping("/admin/getLogsBySearchParam")
//    public ResponseBean getAllLogs(@RequestParam String currentPage,
//                                   @RequestParam String pageSize,
//                                   @RequestParam String searchParam){
//        ResponseBean result = logService.getLogsBySearchParam(searchParam,Integer.parseInt(currentPage),Integer.parseInt(pageSize));
//        System.out.println(result);
//        return result;
//    }

    @PostMapping("/admin/getLogsBySearchParam")
    public ResponseBean getLogsBySearchParam(@RequestBody LogSearchParams logSearchParams){
        ResponseBean result = logService.getLogsBySearchParam(logSearchParams);
        System.out.println(result);
        return result;
    }

    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void Download(HttpServletResponse res) {
        FileDownLoadUtil.Download(res);
    }
}
