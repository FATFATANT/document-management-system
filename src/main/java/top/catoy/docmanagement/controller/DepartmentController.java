package top.catoy.docmanagement.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.docmanagement.domain.Department;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.mapper.DepartmentMapper;
import top.catoy.docmanagement.service.DepartmentService;
import top.catoy.docmanagement.utils.JWTUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-05-06 18:31
 **/
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/admin/getAllDepartments")
    public ResponseBean getDepartmentsTree(){
        ResponseBean result = departmentService.getDepartmentsTree();
        return result;
    }


    @RequestMapping(value = "/admin/getTopDepartment",method = RequestMethod.POST)
    public ResponseBean getTopDepartment(){
        List list = departmentService.getTopDepartment();
        if(list != null){
            return new ResponseBean(ResponseBean.SUCCESS,"查找成功!",list);
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"查找失败",null);
        }
    }

    @GetMapping(value = "/admin/getMyChildDepartments")
    public ResponseBean getMyChildDepartments(){
        List<Department> departments = departmentMapper.getAllDepartments();
        Department rootDepartment;
        int departmentId = JWTUtil.getUserInfo((String) SecurityUtils.getSubject().getPrincipal()).getDepartmentId();
        List list = departmentService.getChild(departmentId,departments);
        rootDepartment = departmentMapper.getDepartmentById(departmentId);
        rootDepartment.setChildren(list);
        List<Department> rootList = new ArrayList<>();
        rootList.add(rootDepartment);
        if(list != null){
            return new ResponseBean(ResponseBean.SUCCESS,"查找成功!",rootList);
        }else {
            return new ResponseBean(ResponseBean.FAILURE,"查找失败",null);
        }
    }





    @RequestMapping(value = "/admin/getDepartmentList",method = RequestMethod.POST)
    public ResponseBean getDepartmentList(){
        return null;
    }
}
