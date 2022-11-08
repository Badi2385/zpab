package pl.gov.orlikiapi.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.role.model.Role;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("view/roles")
    public String viewRolesPage(Model model) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "roles";
    }

    @GetMapping("view/showNewRoleForm")
    public String showNewRoleForm(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "new_role";
    }

    @PostMapping("view/saveRole")
    public String saveRole(@ModelAttribute("role") Role role) {
        roleService.saveRole(role);
        return "redirect:/view/roles";
    }

    @GetMapping("view/showUpdateRoleForm/{id}")
    public String showUpdateRoleForm(@PathVariable (value = "id") Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "update_role";
    }

    @GetMapping("view/deleteRole/{id}")
    public String deleteRole(@PathVariable (value = "id") Long id) {
        this.roleService.deleteRoleById(id);
        return "redirect:/view/roles";
    }
}
