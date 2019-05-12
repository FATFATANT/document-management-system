package top.catoy.docmanagement.domain;

import java.util.List;

/**
 * @description: 文档标签
 * @author: xjn
 * @create: 2019-04-26 08:58
 **/
public class DocLabel {
    private int docLabelId;
    private String docLabelName;
    private int superId;

    private List<DocInfo> list;

    public List<DocInfo> getList() {
        return list;
    }

    public void setList(List<DocInfo> list) {
        this.list = list;
    }

    public int getDocLabelId() {
        return docLabelId;
    }

    public void setDocLabelId(int docLabelId) {
        this.docLabelId = docLabelId;
    }

    public String getDocLabelName() {
        return docLabelName;
    }

    public void setDocLabelName(String docLabelName) {
        this.docLabelName = docLabelName;
    }

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    @Override
    public String toString() {
        return "DocLabel{" +
                "docLabelId=" + docLabelId +
                ", docLabelName='" + docLabelName + '\'' +
                ", superId=" + superId +
                ", list=" + list +
                '}';
    }
}
