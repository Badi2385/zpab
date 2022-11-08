package pl.gov.orlikiapi.sportsvenue;

import pl.gov.orlikiapi.sportsfield.model.SportsField;
import pl.gov.orlikiapi.sportsvenue.model.SportsVenue;

import java.util.List;

public interface SportsVenueService {
    List<SportsVenue> getAllSportsVenues();
    void saveSportsVenue(SportsVenue sportsVenue);
    SportsVenue getSportsVenueById(Long id);
    void deleteSportsVenueById(Long id);
}
