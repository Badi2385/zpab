package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;

import java.util.List;
import java.util.Optional;

@Service
public class SportsFieldReservationServiceImpl implements SportsFieldReservationService{

    @Autowired
    private SportsFieldReservationRepository sportsFieldReservationRepository;

    @Override
    public List<SportsFieldReservation> getAllSportsFieldReservations() {
        return sportsFieldReservationRepository.findAll();
    }

    @Override
    public void saveSportsFieldReservation(SportsFieldReservation sportsFieldReservation) {
        this.sportsFieldReservationRepository.save(sportsFieldReservation);
    }

    @Override
    public SportsFieldReservation getSportsFieldReservationById(Long id) {
        Optional<SportsFieldReservation> optional = sportsFieldReservationRepository.findById(id);
        SportsFieldReservation sportsFieldReservation = null;
        if (optional.isPresent()) {
            sportsFieldReservation = optional.get();
        } else {
            throw new RuntimeException("SportsFieldReservation not found for id :: " + id);
        }
        return sportsFieldReservation;
    }

    @Override
    public void deleteSportsFieldReservationById(Long id) {
        this.sportsFieldReservationRepository.deleteById(id);
    }
}
