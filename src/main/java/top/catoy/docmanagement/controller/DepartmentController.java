package top.catoy.docmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.service.DepartmentService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-05-06 18:31
 **/
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/admin/getAllDepartments")
    public ResponseBean getDepartmentsTree(){
        ResponseBean result = departmentService.getDepartmentsTree();
        return result;
    }
}
