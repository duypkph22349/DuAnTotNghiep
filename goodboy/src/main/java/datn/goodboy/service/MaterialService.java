package datn.goodboy.service;


import datn.goodboy.model.entity.Material;
import datn.goodboy.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    public Page<Material> getPage(Pageable pageable){
        return materialRepository.findAll(pageable);
    }

    public ArrayList<Material> getAll(){
        return (ArrayList<Material>) materialRepository.findAll();
    }


    public Material saveMaterial(Material material) {

        return materialRepository.save(material);
    }

    public void deleteMaterial(int id) {
        materialRepository.deleteById(id);
    }

    public Optional<Material> findByIdBrand(int id) {
        return materialRepository.findById(id);
    }
}
