package top.catoy.docmanagement.domain;


/**
 * @description: 日志搜索参数封装
 * @author: xjn
 * @create: 2019-05-03 00:28
 **/
public class LogSearchParams {
    private String opName;
    private String startTime;
    private String endTime;
    private String opLabel;
    private String userName;
    private int currentPage;
    private int pageSize;
    private int userId;

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LogSearchParams{" +
                "opName='" + opName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", opLabel='" + opLabel + '\'' +
                ", userName='" + userName + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", userId=" + userId +
                '}';
    }
}
