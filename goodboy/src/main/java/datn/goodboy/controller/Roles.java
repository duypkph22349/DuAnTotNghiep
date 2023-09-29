package datn.goodboy.controller;

import datn.goodboy.model.entity.Employee;

import datn.goodboy.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.UUID;

@RequestMapping("admin/roles")
@Controller
public class Roles {

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

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        rolesService.deleteRoles(id);
        return "redirect:/admin/roles/hien-thi";
    }
    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") int id){
        Optional<datn.goodboy.model.entity.Roles> roles = rolesService.findByIdRoles(id);
        if (roles.isPresent()) {
            model.addAttribute("detail", roles.get());
        } else {
            model.addAttribute("detail", null);
        }
        return "";
    }

}
