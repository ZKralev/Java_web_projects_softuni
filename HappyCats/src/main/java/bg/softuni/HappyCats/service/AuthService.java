package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.UserRegistrationDTO;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
                passwordEncoder.encode(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getFullname(),
                userRegistrationDTO.getAge()
        );
        this.userRepository.save(user);
    }
}
