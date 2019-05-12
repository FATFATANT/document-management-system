package top.catoy.docmanagement.domain;

import java.util.Arrays;

public class DocTableInfo {

    private String fileName;

    private  int id;

    private String[] tags;

    private String type;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DocTableInfo{" +
                "fileName='" + fileName + '\'' +
                ", id=" + id +
                ", tags=" + Arrays.toString(tags) +
                ", type='" + type + '\'' +
                '}';
    }
}
