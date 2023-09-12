package datn.goodboy.service;


import datn.goodboy.model.entity.Size;
import datn.goodboy.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }


    public Page<Size> getPage(Pageable pageable){
        return sizeRepository.findAll(pageable);
    }

    public ArrayList<Size> getAll(){
        return (ArrayList<Size>) sizeRepository.findAll();
    }


    public Size saveSize(Size size) {

        return sizeRepository.save(size);
    }

    public void deleteSize(int id) {
        sizeRepository.deleteById(id);
    }

    public Optional<Size> findByIdSize(int id) {
        return sizeRepository.findById(id);
    }
}
