package pl.gov.orlikiapi.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gov.orlikiapi.role.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
