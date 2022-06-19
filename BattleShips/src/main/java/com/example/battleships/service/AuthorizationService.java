package com.example.battleships.service;

import java.util.Optional;

import com.example.battleships.model.dtos.UserLoginDTO;
import com.example.battleships.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.battleships.model.Users;
import com.example.battleships.model.dtos.UserRegistrationDTO;
import com.example.battleships.repositories.UserRepository;

@Service
public class AuthorizationService {

    private Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);

    private UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public AuthorizationService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationDTO userRegistrationDTO){

        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            throw new RuntimeException("password.match");
        }

        Users user = new Users(
                userRegistrationDTO.getUsername(),
                userRegistrationDTO.getFullName(),
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getPassword()
        );

        this.userRepository.save(user);
    }


    public void logout() {
        currentUser.logout();
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<Users> userOpt = userRepository.
                findByUsername(userLoginDTO.getUsername());

        if (userOpt.isEmpty()) {
            LOGGER.info("User not found. User name: {}",
                    userLoginDTO.getUsername());
            return false;
        }

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = userOpt.get().getPassword();

        boolean success = rawPassword.equals(encodedPassword);

        if (success) {
            login(userOpt.get());
        } else {
            logout();
        }

        return success;
    }

    private void login(Users userEntity) {
        currentUser.
                setLoggedIn(true).
                setName(userEntity.getFullName()).
                setEmail(userEntity.getEmail());
    }

}
