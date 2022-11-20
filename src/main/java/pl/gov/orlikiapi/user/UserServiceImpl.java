package pl.gov.orlikiapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.user.dto.UserRegistrationDto;
import pl.gov.orlikiapi.user.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getUsername(),
                passwordEncoder.encode(userRegistrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getRoles().isEmpty())
        {
            throw new RuntimeException("You must provide Name, Password and Role for the new user");
        }
        else
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // To be able to delete the user after manual update, we must add new role into roles table
            List<Role> roleList = new ArrayList<>();
            for(Role role : user.getRoles())
            {
                roleList.add(new Role(role.getName()));
            }
            user.setRoles(roleList);
            this.userRepository.save(user);
        }
    }

    @Override
    public void updateUser(User user) {
        if (user.getUsername().isEmpty() || user.getRoles().isEmpty())
        {
            throw new RuntimeException("You must provide Name, Password and Role for the new user");
        }
        else
        {
            User toBeUpdated = getUserById(user.getId());
            toBeUpdated.setUsername(user.getUsername());
            toBeUpdated.setRoles(user.getRoles());
            this.userRepository.save(toBeUpdated);
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found for id :: " + id);
        }
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
