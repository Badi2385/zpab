package pl.gov.orlikiapi.sportsvenue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsvenue.model.SportsVenue;

import java.util.List;
import java.util.Optional;

@Service
public class SportsVenueServiceImpl implements SportsVenueService{

    @Autowired
    private SportsVenueRepository sportsVenueRepository;

    @Override
    public List<SportsVenue> getAllSportsVenues() {
        return sportsVenueRepository.findAll();
    }

    @Override
    public void saveSportsVenue(SportsVenue sportsVenue) {
        this.sportsVenueRepository.save(sportsVenue);
    }

    @Override
    public SportsVenue getSportsVenueById(Long id) {
        Optional<SportsVenue> optional = sportsVenueRepository.findById(id);
        SportsVenue sportsVenue = null;
        if (optional.isPresent()) {
            sportsVenue = optional.get();
        } else {
            throw new RuntimeException("SportsVenue not found for id :: " + id);
        }
        return sportsVenue;
    }

    @Override
    public void deleteSportsVenueById(Long id) {
        this.sportsVenueRepository.deleteById(id);
    }
}
