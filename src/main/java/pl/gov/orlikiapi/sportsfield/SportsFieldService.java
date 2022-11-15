package pl.gov.orlikiapi.sportsfield;

import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfield.model.SportsField;

import java.util.List;

public interface SportsFieldService {
    List<SportsField> getAllSportsFields();
    List<SportsField> getAllSportsVenuesSortedByCity();
    List<SportsField> getAllSportsVenuesSortedByStreet();
    List<SportsField> getAllSportsVenuesSortedByType();
    List<SportsField> getAllSportsVenuesSortedByCityAndType();
    List<SportsField> getAllSportsVenuesSortedByStreetAndType();
    List<SportsField> getAllSportsVenuesSortedByStreetAndCity();
    List<SportsField> getAllSportsVenuesSortedByStreetAndCityAndType();
    void saveSportsField(SportsField sportsField);
    SportsField getSportsFieldById(Long id);
    void deleteSportsFieldById(Long id);
}
