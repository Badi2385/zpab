package pl.gov.orlikiapi.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.user.dto.UserRegistrationDto;
import pl.gov.orlikiapi.user.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);

    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
}
