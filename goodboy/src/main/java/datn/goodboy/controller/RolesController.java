package datn.goodboy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.service.RolesService;

@RequestMapping("admin/roles")
@Controller
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("list", rolesService.getAllRoles());
        return "admin/pages/roles/table-roles";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute datn.goodboy.model.entity.Roles roles) {
        rolesService.saveRoles(roles);
        return "redirect:/admin/roles/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        rolesService.deleteRoles(id);
        return "redirect:/admin/roles/hien-thi";
    }
    @GetMapping("edit/{id}")
    public String detail(Model model, @PathVariable("id") int id){
        Optional<datn.goodboy.model.entity.Roles> roles = rolesService.findByIdRoles(id);
        if (roles.isPresent()) {
            model.addAttribute("detail", roles.get());
        } else {
            model.addAttribute("detail", null);
        }
        return "admin/pages/roles/create-roles";
    }



}
