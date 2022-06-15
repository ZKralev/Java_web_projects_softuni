package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.DTOS.UserRegistrationDTO;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegistrationDTO userRegistrationDTO){

        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            throw new RuntimeException("password.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        if(byEmail.isPresent()){
            throw new RuntimeException("email.used");
        }

        Optional<User> byUserName = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
        if(byUserName.isPresent()){
            throw new RuntimeException("username.used");
        }

        User user = new User(
                userRegistrationDTO.getUsername(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getFullname(),
                userRegistrationDTO.getAge()
        );

        this.userRepository.save(user);

    }
}
