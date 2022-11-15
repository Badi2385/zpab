package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.data.domain.Page;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;

import java.util.List;

public interface SportsFieldReservationService {
    List<SportsFieldReservation> getAllSportsFieldReservations();

    void saveSportsFieldReservation(SportsFieldReservation sportsFieldReservation);

    SportsFieldReservation getSportsFieldReservationById(Long id);

    void deleteSportsFieldReservationById(Long id);

    Page<SportsFieldReservation> findPaginated(int pageNo, int pageSize);
}
