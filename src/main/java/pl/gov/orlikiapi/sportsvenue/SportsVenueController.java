package pl.gov.orlikiapi.sportsvenue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsvenue.model.SportsVenue;

@Controller
public class SportsVenueController {

    @Autowired
    private SportsVenueService sportsVenueService;

    @GetMapping("view/sportsVenues")
    public String viewRolesPage(Model model) {
        model.addAttribute("listSportsVenues", sportsVenueService.getAllSportsVenues());
        return "sportsVenues";
    }

    @GetMapping("view/showNewSportsVenueForm")
    public String showNewSportsVenueForm(Model model) {
        SportsVenue sportsVenue = new SportsVenue();
        model.addAttribute("sportsVenue", sportsVenue);
        return "new_sportsVenue";
    }

    @PostMapping("view/saveSportsVenue")
    public String saveSportsVenue(@ModelAttribute("sportsVenue") SportsVenue sportsVenue) {
        sportsVenueService.saveSportsVenue(sportsVenue);
        return "redirect:/view/sportsVenues";
    }

    @GetMapping("view/showUpdateSportsVenueForm/{id}")
    public String showUpdateSportsVenueForm(@PathVariable(value = "id") Long id, Model model) {
        SportsVenue sportsVenue = sportsVenueService.getSportsVenueById(id);
        model.addAttribute("sportsVenue", sportsVenue);
        return "update_sportsVenue";
    }

    @GetMapping("view/deleteSportsVenue/{id}")
    public String deleteSportsVenue(@PathVariable (value = "id") Long id) {
        this.sportsVenueService.deleteSportsVenueById(id);
        return "redirect:/view/sportsVenues";
    }
}
