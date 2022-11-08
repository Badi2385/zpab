package pl.gov.orlikiapi.sportsfieldtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.exception.ResourceNotFoundException;
import pl.gov.orlikiapi.sportsfieldtype.model.SportsFieldType;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SportsFieldTypeRestController {

    @Autowired
    private SportsFieldTypeRepository sportsFieldTypeRepository;

    @GetMapping("sportsFieldTypes")
    public List<SportsFieldType> getAllSportsFieldTypes(){
        return sportsFieldTypeRepository.findAll();
    }

    @GetMapping("sportsFieldTypes/{id}")
    public ResponseEntity<SportsFieldType> getSportsFieldTypeById(@PathVariable(value = "id") Long sportsFieldTypeId)
            throws ResourceNotFoundException {
        SportsFieldType sportsFieldType = sportsFieldTypeRepository.findById(sportsFieldTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsFieldType not found for this id : " + sportsFieldTypeId));
        return ResponseEntity.ok().body(sportsFieldType);
    }

    @PostMapping("sportsFieldTypes")
    public SportsFieldType createSportsFieldType(@Valid @RequestBody SportsFieldType sportsFieldType) {
        return sportsFieldTypeRepository.save(sportsFieldType);
    }

    @PutMapping("sportsFieldTypes/{id}")
    public ResponseEntity<SportsFieldType> updateSportsFieldType(@PathVariable(value = "id") Long sportsFieldTypeId,
                                                         @Valid @RequestBody SportsFieldType sportsFieldTypeDetails) throws ResourceNotFoundException {
        SportsFieldType sportsFieldType = sportsFieldTypeRepository.findById(sportsFieldTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsFieldType not found for this id : " + sportsFieldTypeId));

        sportsFieldType.setType(sportsFieldTypeDetails.getType());
        final SportsFieldType updatedSportsFieldType = sportsFieldTypeRepository.save(sportsFieldType);
        return ResponseEntity.ok(updatedSportsFieldType);

    }

    @DeleteMapping("sportsFieldTypes/{id}")
    public Map<String, Boolean> deleteSportsFieldType(@PathVariable(value = "id") Long sportsFieldTypeId) throws ResourceNotFoundException{
        SportsFieldType sportsFieldType = sportsFieldTypeRepository.findById(sportsFieldTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("SportsFieldType not found for this id : " + sportsFieldTypeId));

        sportsFieldTypeRepository.delete(sportsFieldType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
