package pl.gov.orlikiapi.sportsfield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfield.model.SportsField;

@Controller
public class SportsFieldController {

    private Boolean sortByCity = false;
    private Boolean sortByStreet = false;
    private Boolean sortByType = false;


    @Autowired
    private SportsFieldService sportsFieldService;

    @GetMapping("view/sportsFields")
    public String viewRolesPage(Model model) {

        if(sortByCity && !sortByStreet && !sortByType)
        {
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsVenuesSortedByCity());
        }
        else if (!sortByCity && sortByStreet && !sortByType){
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsVenuesSortedByStreet());
        }
        else if (!sortByCity && !sortByStreet && sortByType){
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsVenuesSortedByType());
        }
        else if (!sortByStreet && sortByCity && sortByType){
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsVenuesSortedByCityAndType());
        }
        else if (sortByStreet && !sortByCity && sortByType){
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsVenuesSortedByStreetAndType());
        }
        else if (sortByStreet && sortByCity && !sortByType){
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsVenuesSortedByStreetAndCity());
        }
        else if (sortByStreet && sortByCity && sortByType){
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsVenuesSortedByStreetAndCityAndType());
        }
        else {
            model.addAttribute("listSportsFields", sportsFieldService.getAllSportsFields());
        }
        return "sportsFields";
    }

    @GetMapping("view/sortFieldsByCity")
    public String viewRolesPageByCity(Model model) {
        sortByCity = !sortByCity;
        return "redirect:/view/sportsFields";
    }

    @GetMapping("view/sortFieldsByStreet")
    public String viewRolesPageByStreet(Model model) {
        sortByStreet = !sortByStreet;
        return "redirect:/view/sportsFields";
    }

    @GetMapping("view/sortFieldsByType")
    public String viewRolesPageByType(Model model) {
        sortByType = !sortByType;
        return "redirect:/view/sportsFields";
    }

    @GetMapping("view/showNewSportsFieldForm")
    public String showNewRoleForm(Model model) {
        SportsField sportsField = new SportsField();
        model.addAttribute("sportsField", sportsField);
        return "new_sportsField";
    }

    @PostMapping("view/saveSportsField")
    public String saveSportsField(@ModelAttribute("sportsField") SportsField sportsField) {
        sportsFieldService.saveSportsField(sportsField);
        return "redirect:/view/sportsFields";
    }

    @GetMapping("view/showUpdateSportsFieldForm/{id}")
    public String showUpdateSportsFieldForm(@PathVariable(value = "id") Long id, Model model) {
        SportsField sportsField = sportsFieldService.getSportsFieldById(id);
        model.addAttribute("sportsField", sportsField);
        return "update_sportsField";
    }

    @GetMapping("view/deleteSportsField/{id}")
    public String deleteRole(@PathVariable (value = "id") Long id) {
        this.sportsFieldService.deleteSportsFieldById(id);
        return "redirect:/view/sportsFields";
    }
}
