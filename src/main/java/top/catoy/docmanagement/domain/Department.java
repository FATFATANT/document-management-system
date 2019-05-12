package top.catoy.docmanagement.domain;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 部门
 * @author: xjn
 * @create: 2019-04-26 08:50
 **/
public class Department {
    private int id;
    private String name;
    private int parent_id;
    private String instroduction;

    private int sort;
    private List<Department> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getInstroduction() {
        return instroduction;
    }

    public void setInstroduction(String instroduction) {
        this.instroduction = instroduction;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", instroduction='" + instroduction + '\'' +
                ", sort=" + sort +
                ", children=" + children +
                '}';
    }
}
