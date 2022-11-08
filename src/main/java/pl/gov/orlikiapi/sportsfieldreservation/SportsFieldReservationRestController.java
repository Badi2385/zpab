package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.exception.ResourceNotFoundException;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SportsFieldReservationRestController {

    @Autowired
    private SportsFieldReservationRepository sportsFieldReservationRepository;

    @GetMapping("sportsFieldReservations")
    public List<SportsFieldReservation> getAllSportsFieldReservations(){
        return sportsFieldReservationRepository.findAll();
    }

    @GetMapping("sportsFieldReservations/{id}")
    public ResponseEntity<SportsFieldReservation> getSportsFieldReservationById(@PathVariable(value = "id") Long sportsFieldReservationId)
            throws ResourceNotFoundException {
        SportsFieldReservation sportsFieldReservation = sportsFieldReservationRepository.findById(sportsFieldReservationId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsFieldReservation not found for this id : " + sportsFieldReservationId));
        return ResponseEntity.ok().body(sportsFieldReservation);
    }

    @PostMapping("sportsFieldReservations")
    public SportsFieldReservation createSportsFieldReservation(@Valid @RequestBody SportsFieldReservation sportsFieldReservation) {
        return sportsFieldReservationRepository.save(sportsFieldReservation);
    }

    @PutMapping("sportsFieldReservations/{id}")
    public ResponseEntity<SportsFieldReservation> updateSportsFieldReservation(@PathVariable(value = "id") Long sportsFieldReservationId,
                                                         @Valid @RequestBody SportsFieldReservation sportsFieldReservationDetails) throws ResourceNotFoundException {
        SportsFieldReservation sportsFieldReservation = sportsFieldReservationRepository.findById(sportsFieldReservationId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsFieldReservation not found for this id : " + sportsFieldReservationId));

        sportsFieldReservation.setStartDate(sportsFieldReservationDetails.getStartDate());
        sportsFieldReservation.setEndDate(sportsFieldReservationDetails.getEndDate());
        final SportsFieldReservation updatedSportsFieldReservation = sportsFieldReservationRepository.save(sportsFieldReservation);
        return ResponseEntity.ok(updatedSportsFieldReservation);

    }

    @DeleteMapping("sportsFieldReservations/{id}")
    public Map<String, Boolean> deleteSportsFieldReservation(@PathVariable(value = "id") Long sportsFieldReservationId) throws ResourceNotFoundException{
        SportsFieldReservation sportsFieldReservation = sportsFieldReservationRepository.findById(sportsFieldReservationId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsFieldReservation not found for this id : " + sportsFieldReservationId));

        sportsFieldReservationRepository.delete(sportsFieldReservation);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
