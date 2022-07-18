package bg.softuni.HappyCats.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PetsUserDetails implements UserDetails {

  private final String password;
  private final String username;
  private final String fullName;
  private final GrantedAuthority authorities;

  public PetsUserDetails(String password,
                             String username,
                             String fullName,
                             GrantedAuthority authorities) {
    this.password = password;
    this.username = username;
    this.fullName = fullName;
    this.authorities = authorities;
  }

  public String getFullName() {
    return fullName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(authorities);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
