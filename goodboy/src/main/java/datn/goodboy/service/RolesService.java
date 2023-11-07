package datn.goodboy.service;

import datn.goodboy.model.entity.Roles;
import datn.goodboy.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
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

    public void deleteRoles(int id) {

        rolesRepository.deleteById(id);
    }

    public Optional<Roles> findByIdRoles(int id) {

        return rolesRepository.findById(id);
    }

    public Roles getNewEmployeeRole() {
        Optional<Roles> roles = rolesRepository.getNewEmployeeRole();
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
    }

    public Roles getEmployeeRole() {
        Optional<Roles> roles = rolesRepository.getNewEmployeeRole();
        if (roles.isPresent()) {
            return roles.get();
        } else {
            Roles newEmpRoles = new Roles();
            newEmpRoles.setName("NEWEMPLOYEE");
            newEmpRoles.setRole("EMPLOYEE");
            newEmpRoles.setDeleted(false);
            newEmpRoles.setUpdatedAt(LocalDateTime.now());
            newEmpRoles.setStatus(0);
            return rolesRepository.save(newEmpRoles);
        }
        // else throws exeption
    }
}
