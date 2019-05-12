package top.catoy.docmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.Department;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.mapper.DepartmentMapper;
import top.catoy.docmanagement.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public String getDepartmentNameById(int id) {
        return departmentMapper.getDepartmentNameById(id);
    }

    public int getDepartmentIdByName(String name){
        return departmentMapper.getDepartmentIdByName(name);
    }

    @Override
    public ResponseBean getAllDepartments() {
        try {
          List<Department> departments = departmentMapper.getAllDepartments();
          if(departments != null){
              return new ResponseBean(ResponseBean.SUCCESS,"查询成功",departments);
          }else{
              return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
          }
        }catch (RuntimeException r){
            return new ResponseBean(ResponseBean.ERROR,"错误",null);
        }
    }

    @Override
    public ResponseBean getDepartmentsTree() {
        try {
            List<Department> departments = departmentMapper.getAllDepartments();
            List<Department> fatherList = new ArrayList<>();
            if(departments != null){
                departments.forEach((department) ->{
                    if (department.getParent_id() == 0){
                        fatherList.add(department);
                    }
                });
                for (Department department : fatherList) {
                    department.setChildren(getChild(department.getId(), departments));
                }
                return new ResponseBean(ResponseBean.SUCCESS,"查询成功",fatherList);

            }else {
                return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
            }

        }catch (RuntimeException r){
            return new ResponseBean(ResponseBean.ERROR,"错误",null);
        }
    }

    private List<Department> getChild(int id, List<Department> fatherList) {
        List<Department> childList = new ArrayList<>();
        int count = 0;
        for (Department department : fatherList) {
            // 遍历所有节点，将父id与传过来的id比较
            if (department.getParent_id()== id) {
                department.setSort(count);
                childList.add(department);
                count++;
            }
        }
        for (Department department : childList) {

                department.setChildren(getChild(department.getId(), fatherList));

        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }

        System.out.println(childList.toString());
        return childList;
    }
}
