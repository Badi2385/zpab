package pl.gov.orlikiapi.sportsfieldtype;

import pl.gov.orlikiapi.sportsfield.model.SportsField;
import pl.gov.orlikiapi.sportsfieldtype.model.SportsFieldType;

import java.util.List;

public interface SportsFieldTypeService {
    List<SportsFieldType> getAllSportsFieldTypes();
    void saveSportsFieldType(SportsFieldType sportsFieldType);
    SportsFieldType getSportsFieldTypeById(Long id);
    void deleteSportsFieldTypeById(Long id);
}
