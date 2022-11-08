package pl.gov.orlikiapi.sportsfieldtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfieldtype.model.SportsFieldType;

import java.util.List;
import java.util.Optional;

@Service
public class SportsFieldTypeServiceImpl implements SportsFieldTypeService{

    @Autowired
    private SportsFieldTypeRepository sportsFieldTypeRepository;

    @Override
    public List<SportsFieldType> getAllSportsFieldTypes() {
        return sportsFieldTypeRepository.findAll();
    }

    @Override
    public void saveSportsFieldType(SportsFieldType sportsFieldType) {
        this.sportsFieldTypeRepository.save(sportsFieldType);
    }

    @Override
    public SportsFieldType getSportsFieldTypeById(Long id) {
        Optional<SportsFieldType> optional = sportsFieldTypeRepository.findById(id);
        SportsFieldType sportsFieldType = null;
        if (optional.isPresent()) {
            sportsFieldType = optional.get();
        } else {
            throw new RuntimeException("SportsFieldType not found for id :: " + id);
        }
        return sportsFieldType;
    }

    @Override
    public void deleteSportsFieldTypeById(Long id) {
        this.sportsFieldTypeRepository.deleteById(id);
    }
}
