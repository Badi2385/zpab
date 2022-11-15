package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfield.SportsFieldService;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;
import pl.gov.orlikiapi.user.UserRepository;
import pl.gov.orlikiapi.user.UserService;
import pl.gov.orlikiapi.user.model.User;

import java.util.List;

@Controller
public class SportsFieldReservationController {

    @Autowired
    private SportsFieldReservationService sportsFieldReservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SportsFieldService sportsFieldService;

    @GetMapping("view/reservations/{pageNo}")
    public String viewPaginatedReservations(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;

        Page<SportsFieldReservation> page = sportsFieldReservationService.findPaginated(pageNo, pageSize);
        List<SportsFieldReservation> reservationList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listReservations", reservationList);
        return "reservations";
    }

    @GetMapping("view/reservations")
    public String viewReservationsPage(Model model) {
//        model.addAttribute("listReservations", sportsFieldReservationService.getAllSportsFieldReservations());
        return viewPaginatedReservations(1, model);
    }

    @GetMapping("view/showNewReservationForm")
    public String showNewReservationForm(Model model) {
        SportsFieldReservation sportsFieldReservation = new SportsFieldReservation();
        model.addAttribute("sportsFieldReservation", sportsFieldReservation);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("sportsFields", sportsFieldService.getAllSportsFields());
        return "new_reservation";
    }

    @PostMapping("view/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") SportsFieldReservation sportsFieldReservation) {
        sportsFieldReservationService.saveSportsFieldReservation(sportsFieldReservation);
        return "redirect:/view/reservations";
    }

    @GetMapping("view/showUpdateReservationForm/{id}")
    public String showUpdateReservationForm(@PathVariable(value = "id") Long id, Model model) {
        SportsFieldReservation sportsFieldReservation = sportsFieldReservationService.getSportsFieldReservationById(id);
        model.addAttribute("sportsFieldReservation", sportsFieldReservation);
        return "update_reservation";
    }

    @GetMapping("view/deleteReservation/{id}")
    public String deleteReservation(@PathVariable (value = "id") Long id) {
        this.sportsFieldReservationService.deleteSportsFieldReservationById(id);
        return "redirect:/view/reservations";
    }
}
