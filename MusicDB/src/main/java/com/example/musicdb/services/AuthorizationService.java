package com.example.musicdb.services;

import com.example.musicdb.model.dtos.UserLoginDTO;
import com.example.musicdb.model.dtos.UserRegistrationDTO;
import com.example.musicdb.model.entity.UserEntity;
import com.example.musicdb.repositories.UserRepository;
import com.example.musicdb.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService {


    private Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);

    private UserRepository userRepository;
    private CurrentUser currentUser;

    public AuthorizationService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void register(UserRegistrationDTO userRegistrationDTO) {
        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            throw new RuntimeException("password.match");
        }

        UserEntity user = new UserEntity(
                userRegistrationDTO.getUsername(),
                userRegistrationDTO.getFullName(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getEmail()
        );

        this.userRepository.save(user);
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> userOpt = userRepository.
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

    private void login(UserEntity userEntity) {
        currentUser.
                setLoggedIn(true).
                setName(userEntity.getFullName()).
                setEmail(userEntity.getEmail());
    }

    public void logout() {
        currentUser.logout();
    }
}
