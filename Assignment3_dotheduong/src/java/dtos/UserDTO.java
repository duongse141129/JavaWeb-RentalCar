/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;



/**
 *
 * @author minhv
 */
public class UserDTO {
    private String email;
    private String fullName;
    private String password;
    private String phone;
    private String address;
    private String createDate;
    private String roleID;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(String email, String fullName, String password, String phone, String address, String createDate, String roleID, boolean status) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.roleID = roleID;
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

 

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "email=" + email + ", fullName=" + fullName + ", password=" + password + ", phone=" + phone + ", address=" + address + ", createDate=" + createDate + ", roleID=" + roleID + ", status=" + status + '}';
    }
    

    
    
}
