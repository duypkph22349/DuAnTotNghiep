package datn.goodboy.service;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.repository.BrandRepository;
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

    public void delete(Integer id) {
        Brand brand = brandRepository.findById(id).get();
        brandRepository.delete(brand);
    }

}
