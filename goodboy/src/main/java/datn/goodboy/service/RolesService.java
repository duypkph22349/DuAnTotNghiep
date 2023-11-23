package datn.goodboy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Roles;
import datn.goodboy.repository.RolesRepository;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Page<Roles> getPage(Pageable pageable) {
        return rolesRepository.findAll(pageable);
    }

    public ArrayList<Roles> getAllRoles() {
        return (ArrayList<Roles>) rolesRepository.findAll();
    }

    public Roles saveRoles(Roles roles) {

        return rolesRepository.save(roles);
    }


    public void deleteRoles(Integer id) {
        Optional<Roles> roles = rolesRepository.findById(id);
        if (roles.isPresent()) {
            if (roles.get().isDeleted()) {
                roles.get().setDeleted(false);
            } else {
                roles.get().setDeleted(true);
            }
            rolesRepository.save(roles.get());
        }
    }

    public Optional<Roles> findByIdRoles(int id) {

        return rolesRepository.findById(id);
    }


    public Roles getEmployeeRole() {
        Optional<Roles> roles = rolesRepository.getEmployeeRole();
        if (roles.isPresent()) {
            return roles.get();
        } else {
            Roles newEmpRoles = new Roles();
            newEmpRoles.setName("EMPLOYEE");
            newEmpRoles.setRole("ADMIN");
            newEmpRoles.setDeleted(false);
            newEmpRoles.setUpdatedAt(LocalDateTime.now());
            newEmpRoles.setStatus(0);
            return rolesRepository.save(newEmpRoles);
        }
        // else throws exeption
    }

    public Roles getAdmineRole() {
        Optional<Roles> roles = rolesRepository.getAdminRole();
        if (roles.isPresent()) {
            return roles.get();
        } else {
            Roles newEmpRoles = new Roles();
            newEmpRoles.setName("ADMIN");
            newEmpRoles.setRole("ADMIN,STAFF");
            newEmpRoles.setDeleted(false);
            newEmpRoles.setUpdatedAt(LocalDateTime.now());
            newEmpRoles.setStatus(0);
            return rolesRepository.save(newEmpRoles);
        }
        // else throws exeption
    }
}
