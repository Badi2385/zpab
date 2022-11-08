package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;

public interface SportsFieldReservationRepository extends JpaRepository <SportsFieldReservation, Long> {
}
