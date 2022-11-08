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

    @Autowired
    private SportsFieldService sportsFieldService;

    @GetMapping("view/sportsFields")
    public String viewRolesPage(Model model) {
        model.addAttribute("listSportsFields", sportsFieldService.getAllSportsFields());
        return "sportsFields";
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
