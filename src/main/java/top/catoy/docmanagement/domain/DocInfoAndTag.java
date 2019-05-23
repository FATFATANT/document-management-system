package top.catoy.docmanagement.domain;

public class DocInfoAndTag {
    private int id;

    private int docInfo_id;

    private int tag_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocInfo_id() {
        return docInfo_id;
    }

    public void setDocInfo_id(int docInfo_id) {
        this.docInfo_id = docInfo_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    @Override
    public String toString() {
        return "DocInfoAndTag{" +
                "id=" + id +
                ", docInfo_id=" + docInfo_id +
                ", tag_id=" + tag_id +
                '}';
    }
}
