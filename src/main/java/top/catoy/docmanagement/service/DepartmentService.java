package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.Department;
import top.catoy.docmanagement.domain.ResponseBean;

import java.util.List;

public interface DepartmentService {

     String getDepartmentNameById(int id);

     int getDepartmentIdByName(String name);

     ResponseBean getAllDepartments();

     ResponseBean getDepartmentsTree();
}
