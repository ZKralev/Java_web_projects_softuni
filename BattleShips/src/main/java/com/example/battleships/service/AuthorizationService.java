package com.example.battleships.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.battleships.model.Users;
import com.example.battleships.model.dtos.UserRegistrationDTO;
import com.example.battleships.repositories.UserRepository;

@Service
public class AuthorizationService {
    
    private UserRepository userRepository;
   // private ModelMapper mapper;
 
    @Autowired
    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
       // this.mapper = new ModelMapper();
    }

    public void register(UserRegistrationDTO userRegistrationDTO){

        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            throw new RuntimeException("password.match");
        }

        Optional<Users> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        if(byEmail.isPresent()){
            throw new RuntimeException("email.used");
        }

        Optional<Users> byUserName = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
        if(byUserName.isPresent()){
            throw new RuntimeException("username.used");
        }

        Users user = new Users(
                userRegistrationDTO.getUsername(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getFullName()
        );

        this.userRepository.save(user);
    }


    

}
