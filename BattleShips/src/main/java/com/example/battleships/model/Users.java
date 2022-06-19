package com.example.battleships.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
 public class Users {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "user_name", unique = true, nullable = false)
    @Length(min = 3, max = 10)
    private String username;


    @Column(name = "full_name", nullable = false)
    @Length(min = 5, max = 20)
    private String fullName;

    @Length(min = 3)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;


    public Users() {
    }

    public Users(
            @Length(min = 3, max = 10) String username,
            @Length(min = 5, max = 20) String fullName,
            @Email String email,
            @Length(min = 3) String password) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}
