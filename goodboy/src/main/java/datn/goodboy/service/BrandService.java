package datn.goodboy.service;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.repository.BrandRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public Page<Brand> findAllBrand(Pageable pageable) {
        return brandRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<Brand> searchBrandsByKeyword(String keyword, Pageable pageable) {
        return brandRepository.searchByKeyword(keyword, pageable);
    }

    public Brand add(Brand br) {
        return brandRepository.save(br);
    }

    public Brand update(Integer id, Brand brand) {
        Brand brand1 = brandRepository.findById(id).get();
        brand1.setName(brand.getName());
        brand1.setUpdatedAt(brand.getUpdatedAt());
        brand1.setStatus(brand.getStatus());
        return brandRepository.save(brand1);
    }

    public Brand getById(Integer id) {
        return brandRepository.findById(id).get();
    }

    public void delete(int id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            if (brand.get().isDeleted()) {
                brand.get().setDeleted(false);
            } else {
                brand.get().setDeleted(true);
                brand.get().setStatus(0);
            }
            brandRepository.save(brand.get());
        }
    }

    public List<Map<Integer, String>> getCombobox() {
        return brandRepository.getComboBoxMap();
    }

    public void deleteBrand(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            if (brand.get().isDeleted()) {
                brand.get().setDeleted(false);
            } else {
                brand.get().setDeleted(true);
            }
            brandRepository.save(brand.get());
        }
    }

    public List<Brand> getBrandList() {
        return brandRepository.getBrandAble();
    }
}
