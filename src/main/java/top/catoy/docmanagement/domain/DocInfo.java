package top.catoy.docmanagement.domain;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @description: 文档信息
 * @author: xjn
 * @create: 2019-04-26 08:51
 **/
public class DocInfo {
    private int docId;
    private String docName;
    private String docSavePath;
    private int userId;
    private String suffixName;
    private List<Annex> annexes;//附件
    private int departmentId;
    private String departmentName;
    private Date docPostTime;


    private List<DocLabel> docLabels;

    public List<DocLabel> getDocLabels() {
        return docLabels;
    }

    public void setDocLabels(List<DocLabel> docLabels) {
        this.docLabels = docLabels;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocSavePath() {
        return docSavePath;
    }

    public void setDocSavePath(String docSavePath) {
        this.docSavePath = docSavePath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public List<Annex> getAnnexes() {
        return annexes;
    }

    public void setAnnexes(List<Annex> annexes) {
        this.annexes = annexes;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getDocPostTime() {
        return docPostTime;
    }

    public void setDocPostTime(Date docPostTime) {
        this.docPostTime = docPostTime;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "docId=" + docId +
                ", docName='" + docName + '\'' +
                ", docSavePath='" + docSavePath + '\'' +
                ", userId=" + userId +
                ", suffixName='" + suffixName + '\'' +
                ", annexes=" + annexes +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", docPostTime=" + docPostTime +
                ", docLabels=" + docLabels +
                '}';
    }
}
