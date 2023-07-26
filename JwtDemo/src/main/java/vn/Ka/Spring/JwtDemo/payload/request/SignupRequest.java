package vn.Ka.Spring.JwtDemo.payload.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

public class SignupRequest {
    private String userName;
    private String password;
    private String email;
    private String phone;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date created = new Date();
    private boolean status = true;
    private Set<String> listRole;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<String> getListRole() {
        return listRole;
    }

    public void setListRole(Set<String> listRole) {
        this.listRole = listRole;
    }
}
