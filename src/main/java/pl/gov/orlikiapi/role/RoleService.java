package pl.gov.orlikiapi.role;

import pl.gov.orlikiapi.role.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void saveRole(Role role);
    Role getRoleById(Long id);
    void deleteRoleById(Long id);
}
