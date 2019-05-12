package top.catoy.docmanagement.domain;

import java.util.Date;

/**
 * @description: 日志
 * @author: xjn
 * @create: 2019-04-26 08:59
 **/
public class Log {
    private int logId;
    private int userId;
    private String opName;
    private Date opDate;
    private String opLabel;
    private String userName;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getOpLabel() {
        return opLabel;
    }

    public void setOpLabel(String opLabel) {
        this.opLabel = opLabel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", userId=" + userId +
                ", opName='" + opName + '\'' +
                ", opDate=" + opDate +
                ", opLabel='" + opLabel + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
