package pl.gov.orlikiapi.sportsfieldreservation;

import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfield.model.SportsField;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;

import java.util.List;

public interface SportsFieldReservationService {
    List<SportsFieldReservation> getAllSportsFieldReservations();
    void saveSportsFieldReservation(SportsFieldReservation sportsFieldReservation);
    SportsFieldReservation getSportsFieldReservationById(Long id);
    void deleteSportsFieldReservationById(Long id);
}
