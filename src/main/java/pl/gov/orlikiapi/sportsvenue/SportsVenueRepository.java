package pl.gov.orlikiapi.sportsvenue;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gov.orlikiapi.sportsvenue.model.SportsVenue;

public interface SportsVenueRepository extends JpaRepository <SportsVenue, Long> {
}
