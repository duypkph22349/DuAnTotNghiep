package datn.goodboy.service;

import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Size;
import datn.goodboy.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public Page<Size> findAll(Pageable pageable) {
        return sizeRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Size add(Size origin) {
        return sizeRepository.save(origin);
    }

    public Size update(Integer id, Size color) {
        Size color1 = sizeRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setUpdatedAt(color.getUpdatedAt());
        color1.setStatus(color.getStatus());
        return sizeRepository.save(color1);
    }
    public Size getById(Integer id) {
        return sizeRepository.findById(id).get();
    }

    public Page<Size> searchSizeByKeyword(String keyword, Pageable pageable) {
        return sizeRepository.searchByKeyword(keyword, pageable);
    }
}
