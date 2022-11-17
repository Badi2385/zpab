package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public Page<SportsFieldReservation> findPaginated(int pageNo, int pageSize, Boolean sortByCity, Boolean sortByStreet, Boolean sortByType, Boolean sortByEndDate, String findByEndDate, String findByStreet, String findByCity, String findByType) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if(sortByCity)
        {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "sportsField.SportsVenue.city");
            orders.add(order);
        }
        if(sortByStreet)
        {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "sportsField.SportsVenue.street");
            orders.add(order);
        }
        if(sortByEndDate)
        {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "endDate");
            orders.add(order);
        }
        if(sortByType)
        {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "sportsField.SportsFieldType.type");
            orders.add(order);
        }
        if(!sortByCity && !sortByStreet && !sortByEndDate)
        {
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
            orders.add(order);
        }

        //Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("startDate").ascending());
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(orders));

        if(findByEndDate != "")
        {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(findByEndDate);
                return this.sportsFieldReservationRepository.findByEndDateAfter(date, pageable);
            } catch (ParseException e) {
                // Do nothing
            }
        }
        else if (findByStreet != "")
        {
            return this.sportsFieldReservationRepository.findBySportsFieldSportsVenueStreetContaining(findByStreet, pageable);
        }
        else if (findByCity != "")
        {
            return this.sportsFieldReservationRepository.findBySportsFieldSportsVenueCityContaining(findByCity, pageable);
        }
        else if(findByType != "")
        {
            return this.sportsFieldReservationRepository.findBySportsFieldSportsFieldTypeTypeContaining(findByType, pageable);
        }

        return this.sportsFieldReservationRepository.findAll(pageable);
    }
}
