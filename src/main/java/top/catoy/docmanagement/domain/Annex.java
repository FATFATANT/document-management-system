package top.catoy.docmanagement.domain;

/**
 * @description: 附件
 * @author: xjn
 * @create: 2019-05-15 19:59
 **/
public class Annex {
    private int annexId;
    private String annexName;
    private String annexPath;
    private int docId;

    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    public String getAnnexPath() {
        return annexPath;
    }

    public void setAnnexPath(String annexPath) {
        this.annexPath = annexPath;
    }

    public int getAnnexId() {
        return annexId;
    }

    public void setAnnexId(int annexId) {
        this.annexId = annexId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    @Override
    public String toString() {
        return "Annex{" +
                "annexId=" + annexId +
                ", annexName='" + annexName + '\'' +
                ", annexPath='" + annexPath + '\'' +
                ", docId=" + docId +
                '}';
    }
}
