package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;

import java.util.Date;

public interface SportsFieldReservationRepository extends JpaRepository <SportsFieldReservation, Long> {
    Page<SportsFieldReservation> findByEndDateAfter(Date date, Pageable pageable);
    Page<SportsFieldReservation> findBySportsFieldSportsVenueStreetContaining(String findByStreet, Pageable pageable);
    Page<SportsFieldReservation> findBySportsFieldSportsVenueCityContaining(String findByStreet, Pageable pageable);
    Page<SportsFieldReservation> findBySportsFieldSportsFieldTypeTypeContaining(String findByType, Pageable pageable);

}
