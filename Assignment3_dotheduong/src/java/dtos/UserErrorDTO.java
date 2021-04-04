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
public class UserErrorDTO {
    private String emailError;
    private String fullNameError;
    private String passwordError;
    private String confirmError;
    private String phoneError;
    private String addressError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String emailError, String fullNameError, String passwordError, String confirmError, String phoneError, String addressError) {
        this.emailError = emailError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.phoneError = phoneError;
        this.addressError = addressError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }
    
    
}
