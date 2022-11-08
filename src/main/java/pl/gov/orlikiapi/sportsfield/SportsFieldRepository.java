package pl.gov.orlikiapi.sportsfield;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gov.orlikiapi.sportsfield.model.SportsField;

public interface SportsFieldRepository extends JpaRepository <SportsField, Long> {
}
