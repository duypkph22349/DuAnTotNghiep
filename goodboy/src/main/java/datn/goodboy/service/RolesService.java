package datn.goodboy.service;

import datn.goodboy.model.entity.Roles;
import datn.goodboy.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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


    public Page<Roles> getPage(Pageable pageable){
        return rolesRepository.findAll(pageable);
    }

    public ArrayList<Roles> getAllRoles(){
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
}
