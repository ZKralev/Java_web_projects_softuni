package bg.softuni.mobilele.model.validators;

import bg.softuni.mobilele.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {


    private UserRepository userRepository;

    public UniqueUserNameValidator(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        return userRepository.findByEmail(value).isEmpty();
    }
}
