package datn.goodboy.service;

import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Material;
import datn.goodboy.repository.ColorRepository;
import datn.goodboy.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public Page<Material> findAllMaterial(Pageable pageable) {
        return materialRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Material add(Material material) {
        return materialRepository.save(material);
    }

    public Material update(Integer id, Material material) {
        Material material1 = materialRepository.findById(id).get();
        material1.setName(material.getName());
        material1.setUpdatedAt(material.getUpdatedAt());
        material1.setStatus(material.getStatus());
        return materialRepository.save(material1);
    }

    public Material getById(Integer id) {
        return materialRepository.findById(id).get();
    }

    public Page<Material> searchMaterialByKeyword(String keyword, Pageable pageable) {
        return materialRepository.searchByKeyword(keyword, pageable);
    }
}
