package pl.gov.orlikiapi.sportsfieldtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gov.orlikiapi.role.model.Role;
import pl.gov.orlikiapi.sportsfieldtype.model.SportsFieldType;

@Controller
public class SportsFieldTypeController {

    @Autowired
    private SportsFieldTypeService sportsFieldTypeService;

    @GetMapping("view/fieldTypes")
    public String viewRolesPage(Model model) {
        model.addAttribute("listFieldTypes", sportsFieldTypeService.getAllSportsFieldTypes());
        return "fieldTypes";
    }

    @GetMapping("view/showNewFieldTypeForm")
    public String showNewRoleForm(Model model) {
        SportsFieldType sportsFieldType = new SportsFieldType();
        model.addAttribute("fieldType", sportsFieldType);
        return "new_fieldType";
    }

    @PostMapping("view/saveFieldType")
    public String saveFieldType(@ModelAttribute("fieldType") SportsFieldType sportsFieldType) {
        sportsFieldTypeService.saveSportsFieldType(sportsFieldType);
        return "redirect:/view/fieldTypes";
    }

    @GetMapping("view/showUpdateFieldTypeForm/{id}")
    public String showUpdateFieldTypeForm(@PathVariable(value = "id") Long id, Model model) {
        SportsFieldType sportsFieldType = sportsFieldTypeService.getSportsFieldTypeById(id);
        model.addAttribute("fieldType", sportsFieldType);
        return "update_fieldType";
    }

    @GetMapping("view/deleteFieldType/{id}")
    public String deleteRole(@PathVariable (value = "id") Long id) {
        this.sportsFieldTypeService.deleteSportsFieldTypeById(id);
        return "redirect:/view/fieldTypes";
    }
}
