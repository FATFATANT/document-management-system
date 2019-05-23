package top.catoy.docmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.catoy.docmanagement.domain.Department;

import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-27 10:35
 **/
@Mapper
public interface DepartmentMapper {
    int insertDepartment(Department department);

    int deleteDepartmentById(int id);

    List<Department> getAllDepartments();

    public String getDepartmentNameById(int id);

    public int getDepartmentIdByName(String name);

    public List<Department> getTopDepartmemt();

    Department getDepartmentById(int id);

}
