package pl.gov.orlikiapi.sportsfield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.exception.ResourceNotFoundException;
import pl.gov.orlikiapi.sportsfield.model.SportsField;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SportsFieldRestController {

    @Autowired
    private SportsFieldRepository sportsFieldRepository;

    @GetMapping("sportsFields")
    public List<SportsField> getAllSportsFields(){
        return sportsFieldRepository.findAll();
    }

    @GetMapping("sportsFields/{id}")
    public ResponseEntity<SportsField> getSportsFieldById(@PathVariable(value = "id") Long sportsFieldId)
            throws ResourceNotFoundException {
        SportsField sportsField = sportsFieldRepository.findById(sportsFieldId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsField not found for this id : " + sportsFieldId));
        return ResponseEntity.ok().body(sportsField);
    }

    @PostMapping("sportsFields")
    public SportsField createSportsField(@Valid @RequestBody SportsField sportsField) {
        return sportsFieldRepository.save(sportsField);
    }

    @PutMapping("sportsFields/{id}")
    public ResponseEntity<SportsField> updateSportsField(@PathVariable(value = "id") Long sportsFieldId,
                                                         @Valid @RequestBody SportsField sportsFieldDetails) throws ResourceNotFoundException {
        SportsField sportsField = sportsFieldRepository.findById(sportsFieldId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsField not found for this id : " + sportsFieldId));

        sportsField.setSportsFieldType(sportsFieldDetails.getSportsFieldType());
        final SportsField updatedSportsField = sportsFieldRepository.save(sportsField);
        return ResponseEntity.ok(updatedSportsField);

    }

    @DeleteMapping("sportsFields/{id}")
    public Map<String, Boolean> deleteSportsField(@PathVariable(value = "id") Long sportsFieldId) throws ResourceNotFoundException{
        SportsField sportsField = sportsFieldRepository.findById(sportsFieldId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsField not found for this id : " + sportsFieldId));

        sportsFieldRepository.delete(sportsField);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
