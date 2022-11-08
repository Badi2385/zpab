package pl.gov.orlikiapi.sportsfield;

import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfield.model.SportsField;

import java.util.List;

public interface SportsFieldService {
    List<SportsField> getAllSportsFields();
    void saveSportsField(SportsField sportsField);
    SportsField getSportsFieldById(Long id);
    void deleteSportsFieldById(Long id);
}
