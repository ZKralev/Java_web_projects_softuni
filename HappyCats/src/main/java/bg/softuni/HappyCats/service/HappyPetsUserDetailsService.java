package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.enums.UserRoleEnum;
import bg.softuni.HappyCats.model.user.PetsUserDetails;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import bg.softuni.HappyCats.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HappyPetsUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public HappyPetsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.
                findByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(User userEntity) {

        GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" +
                userEntity.getUserRoles().getDeclaringClass().getName());

        PetsUserDetails petsUserDetails = new PetsUserDetails(
                userEntity.getPassword(),
                userEntity.getUsername(),
                userEntity.getFullName(),
                auth);

        return petsUserDetails;
    }


}
