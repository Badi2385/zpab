package pl.gov.orlikiapi.sportsfield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfield.model.SportsField;

import java.util.List;
import java.util.Optional;

@Service
public class SportsFieldServiceImpl implements SportsFieldService{

    @Autowired
    private SportsFieldRepository sportsFieldRepository;
    @Override
    public List<SportsField> getAllSportsFields() {
        return sportsFieldRepository.findAll();
    }

    @Override
    public void saveSportsField(SportsField sportsField) {
        this.sportsFieldRepository.save(sportsField);
    }

    @Override
    public SportsField getSportsFieldById(Long id) {
        Optional<SportsField> optional = sportsFieldRepository.findById(id);
        SportsField sportsField = null;
        if (optional.isPresent()) {
            sportsField = optional.get();
        } else {
            throw new RuntimeException("SportsField not found for id :: " + id);
        }
        return sportsField;
    }

    @Override
    public void deleteSportsFieldById(Long id) {
        this.sportsFieldRepository.deleteById(id);
    }
}
