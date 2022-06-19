package com.example.battleships.model.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegistrationDTO {

    @NotBlank(message = "User's username cannot be empty.")
    @Length(min = 3)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String fullName;
    
    @Email
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String confirmPassword;


    public UserRegistrationDTO() {
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }



    public Object getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @Override
    public String toString() {
        return "UserRegistrationDTO [confirmPassword=" + confirmPassword + ", email=" + email + ", fullName=" + fullName
                + ", password=" + password + ", username=" + username + "]";
    }


    
    
    
    
}
