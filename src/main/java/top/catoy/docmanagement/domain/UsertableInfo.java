package top.catoy.docmanagement.domain;

public class UsertableInfo {
    private int userId;

    private String userName;

    private String password;

    private String department;

    private String realname;

    private String islocked;

    private String role;

    private String permission;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIslocked() {
        return islocked;
    }

    public void setIslocked(String islocked) {
        this.islocked = islocked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UsertableInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", realname='" + realname + '\'' +
                ", islocked='" + islocked + '\'' +
                ", role='" + role + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
