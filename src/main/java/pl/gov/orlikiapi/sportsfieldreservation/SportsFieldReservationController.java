package pl.gov.orlikiapi.sportsfieldreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfield.SportsFieldService;
import pl.gov.orlikiapi.sportsfieldreservation.model.SportsFieldReservation;
import pl.gov.orlikiapi.user.UserRepository;
import pl.gov.orlikiapi.user.UserService;
import pl.gov.orlikiapi.user.model.User;

import java.util.List;

@Controller
public class SportsFieldReservationController {

    private Boolean sortByCity = false;
    private Boolean sortByStreet = false;
    private Boolean sortByEndDate = false;
    private Boolean sortByType = false;
    private String findByEndDate = "";
    private String findByStreet = "";
    private String findByCity = "";
    private String findByType = "";


    @Autowired
    private SportsFieldReservationService sportsFieldReservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SportsFieldService sportsFieldService;

    @GetMapping("view/reservations/{pageNo}")
    public String viewPaginatedReservations(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;

        Page<SportsFieldReservation> page = sportsFieldReservationService.findPaginated(pageNo, pageSize, sortByCity, sortByStreet, sortByType, sortByEndDate, findByEndDate, findByStreet, findByCity, findByType);
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
        String paginatedReservations = viewPaginatedReservations(1, model);
        return paginatedReservations;
    }

    @GetMapping("view/sortReservationsByCity")
    public String viewReservationsPageByCity(Model model) {
        sortByCity = !sortByCity;
        return "redirect:/view/reservations";
    }

    @GetMapping("view/sortReservationsByStreet")
    public String viewReservationsPageByStreet(Model model) {
        sortByStreet = !sortByStreet;
        return "redirect:/view/reservations";
    }

    @GetMapping("view/sortReservationsByEndDate")
    public String viewReservationsPagePageByEndDate(Model model) {
        sortByEndDate = !sortByEndDate;
        return "redirect:/view/reservations";
    }

    @GetMapping("view/sortReservationsByType")
    public String viewReservationsPagePageByType(Model model) {
        sortByType = !sortByType;
        return "redirect:/view/reservations";
    }

    @GetMapping("view/findReservationsByEndDate")
    public String viewReservationsPagePageByEndDate(Model model, @RequestParam String query) {
        findByType = findByStreet = findByCity = "";
        findByEndDate = query;
        return "redirect:/view/reservations";
    }

    @GetMapping("view/findReservationsByCity")
    public String viewReservationsPagePageByCity(Model model, @RequestParam String query) {
        findByType = findByEndDate = findByStreet = "";
        findByCity = query;
        return "redirect:/view/reservations";
    }

    @GetMapping("view/findReservationsByStreet")
    public String viewReservationsPagePageByStreet(Model model, @RequestParam String query) {
        findByType = findByEndDate = findByCity = "";
        findByStreet = query;
        return "redirect:/view/reservations";
    }

    @GetMapping("view/findReservationsByType")
    public String viewReservationsPagePageByType(Model model, @RequestParam String query) {
        findByEndDate = findByCity = findByStreet = "";
        findByType = query;
        return "redirect:/view/reservations";
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
