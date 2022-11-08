package pl.gov.orlikiapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.exception.ResourceNotFoundException;
import pl.gov.orlikiapi.user.model.User;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                               @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));

        user.setUsername(userDetails.getUsername());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);

    }

    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
