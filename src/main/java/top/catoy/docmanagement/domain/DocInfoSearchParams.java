package top.catoy.docmanagement.domain;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-05-18 19:53
 **/
public class DocInfoSearchParams {
    private List<DocLabel> docLabels;
    private List<Tag> tags;
    private int departmentId;
    private String DocName;
    private String selectYear;
    private PageInfo pageInfo;

    public List<DocLabel> getDocLabels() {
        return docLabels;
    }

    public void setDocLabels(List<DocLabel> docLabels) {
        this.docLabels = docLabels;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getSelectYear() {
        return selectYear;
    }

    public void setSelectYear(String selectYear) {
        this.selectYear = selectYear;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "DocInfoSearchParams{" +
                "docLabels=" + docLabels +
                ", tags=" + tags +
                ", departmentId=" + departmentId +
                ", DocName='" + DocName + '\'' +
                ", selectYear='" + selectYear + '\'' +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
