package com.example.registration.Api;

public class Credentials {
    private String firstname,lastname,Email,MobileNumber,password,ConfirmPassword;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public Credentials(String firstname, String lastname, String email, String mobileNumber, String password, String confirmPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        Email = email;
        MobileNumber = mobileNumber;
        this.password = password;
        ConfirmPassword = confirmPassword;

    }
}
