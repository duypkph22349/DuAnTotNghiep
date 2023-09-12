package datn.goodboy.service;



import datn.goodboy.model.entity.Brand;
import datn.goodboy.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    public Page<Brand> getPage(Pageable pageable){
        return brandRepository.findAll(pageable);
    }

    public ArrayList<Brand> getAll(){
        return (ArrayList<Brand>) brandRepository.findAll();
    }


    public Brand saveBrand(Brand brand) {

        return brandRepository.save(brand);
    }

    public void deleteBrand(int id) {
        brandRepository.deleteById(id);
    }

    public Optional<Brand> findByIdBrand(int id) {
        return brandRepository.findById(id);
    }
}
