package pl.gov.orlikiapi.sportsvenue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.exception.ResourceNotFoundException;
import pl.gov.orlikiapi.sportsvenue.model.SportsVenue;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SportsVenueRestController {

    @Autowired
    private SportsVenueRepository sportsVenueRepository;

    @GetMapping("sportsVenues")
    public List<SportsVenue> getAllSportsVenues(){
        return sportsVenueRepository.findAll();
    }

    @GetMapping("sportsVenues/{id}")
    public ResponseEntity<SportsVenue> getSportsVenueById(@PathVariable(value = "id") Long sportsVenueId)
            throws ResourceNotFoundException {
        SportsVenue sportsVenue = sportsVenueRepository.findById(sportsVenueId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsVenue not found for this id : " + sportsVenueId));
        return ResponseEntity.ok().body(sportsVenue);
    }

    @PostMapping("sportsVenues")
    public SportsVenue createSportsVenue(@Valid @RequestBody SportsVenue sportsVenue) {
        return sportsVenueRepository.save(sportsVenue);
    }

    @PutMapping("sportsVenues/{id}")
    public ResponseEntity<SportsVenue> updateSportsVenue(@PathVariable(value = "id") Long sportsVenueId,
                                                         @Valid @RequestBody SportsVenue sportsVenueDetails) throws ResourceNotFoundException {
        SportsVenue sportsVenue = sportsVenueRepository.findById(sportsVenueId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsVenue not found for this id : " + sportsVenueId));

        sportsVenue.setCity(sportsVenueDetails.getCity());
        sportsVenue.setStreet(sportsVenueDetails.getStreet());
        final SportsVenue updatedSportsVenue = sportsVenueRepository.save(sportsVenue);
        return ResponseEntity.ok(updatedSportsVenue);

    }

    @DeleteMapping("sportsVenues/{id}")
    public Map<String, Boolean> deleteSportsVenue(@PathVariable(value = "id") Long sportsVenueId) throws ResourceNotFoundException{
        SportsVenue sportsVenue = sportsVenueRepository.findById(sportsVenueId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsVenue not found for this id : " + sportsVenueId));

        sportsVenueRepository.delete(sportsVenue);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
