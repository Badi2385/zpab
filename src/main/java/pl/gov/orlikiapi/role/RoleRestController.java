package pl.gov.orlikiapi.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.exception.ResourceNotFoundException;
import pl.gov.orlikiapi.role.model.Role;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoleRestController {
    
    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private RoleService roleService;

//    @GetMapping("view/roles")
//    public String viewRolesPage(Model model) {
//        model.addAttribute("listRoles", roleService.getAllRoles());
//        return "roles";
//    }

    @GetMapping("roles")
    public List<Role> getAllUserRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<Role> getUserRoleById(@PathVariable(value = "id") Long userRoleId)
            throws ResourceNotFoundException {
        Role role = roleRepository.findById(userRoleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id : " + userRoleId));
        return ResponseEntity.ok().body(role);
    }

    @PostMapping("roles")
    public Role createUserRole(@Valid @RequestBody Role role) {
        return roleRepository.save(role);
    }

    @PutMapping("roles/{id}")
    public ResponseEntity<Role> updateUserRole(@PathVariable(value = "id") Long userRoleId,
                                               @Valid @RequestBody Role roleDetails) throws ResourceNotFoundException {
        Role role = roleRepository.findById(userRoleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id : " + userRoleId));

        role.setName(roleDetails.getName());
        final Role updatedRole = roleRepository.save(role);
        return ResponseEntity.ok(updatedRole);

    }

    @DeleteMapping("roles/{id}")
    public Map<String, Boolean> deleteUserRole(@PathVariable(value = "id") Long userRoleId) throws ResourceNotFoundException{
        Role role = roleRepository.findById(userRoleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id : " + userRoleId));

        roleRepository.delete(role);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
