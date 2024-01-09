package datn.goodboy.controller;

import java.util.Optional;

import datn.goodboy.model.entity.Roles;
import datn.goodboy.utils.convert.TrangThaiConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import datn.goodboy.service.RolesService;

@RequestMapping("admin/roles")
@Controller
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @Autowired
    TrangThaiConvert convert;

    @ModelAttribute("convert")
    public TrangThaiConvert convert() {
        return convert;
    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("list", rolesService.getAllRoles());
        return "admin/pages/roles/table-roles";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute Roles roles) {
        rolesService.saveRoles(roles);
        return "redirect:/admin/roles/hien-thi";
    }

    @PostMapping("/update")
    public String update(Model model, @ModelAttribute Roles roles) {
        rolesService.saveRoles(roles);
        return "redirect:/admin/roles/hien-thi";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam("id") Integer id) {
        rolesService.deleteRoles(id);
        return "redirect:/admin/roles/hien-thi";
    }
    @GetMapping("edit/{id}")
    public String detail(Model model, @PathVariable("id") int id){
        Optional<Roles> roles = rolesService.findByIdRoles(id);
        if (roles.isPresent()) {
            model.addAttribute("detail", roles.get());
            model.addAttribute("roles", rolesService.getAllRoles());
        } else {
            model.addAttribute("detail", null);
        }
        return "admin/pages/roles/detail-roles";
    }



}
