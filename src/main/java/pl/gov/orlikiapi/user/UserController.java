package pl.gov.orlikiapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.user.model.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("view/users")
    public String viewRolesPage(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("view/showNewUserForm")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("view/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/view/users";
    }

    @GetMapping("view/showUpdateUserForm/{id}")
    public String showUpdateUserForm(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @GetMapping("view/deleteUser/{id}")
    public String deleteRole(@PathVariable (value = "id") Long id) {
        this.userService.deleteUserById(id);
        return "redirect:/view/users";
    }
}
