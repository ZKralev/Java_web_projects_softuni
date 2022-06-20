package com.example.battleships.user;

import com.example.battleships.model.Ships;
import com.example.battleships.model.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class CurrentUser {

  private String name;
  private boolean loggedIn;
  private String email;
  private List<Ships> ships;

  public List<Ships> getShips() {
    return ships;
  }

  public void setShips(List<Ships> ships) {
    this.ships = ships;
  }

  public String getEmail() {
    return email;
  }

  public CurrentUser setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getName() {
    return name;
  }

  public CurrentUser setName(String name) {
    this.name = name;
    return this;
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public boolean isAnonymous() {
    return !isLoggedIn();
  }

  public CurrentUser setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
    return this;
  }

  public void login(Users user){
    this.name = user.getFullName();
    this.email = user.getEmail();
  }

  public void logout() {
    email = null;
    loggedIn = false;
    name = null;
  }
}
