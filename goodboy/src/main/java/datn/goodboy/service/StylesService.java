package datn.goodboy.service;

import datn.goodboy.model.entity.Size;
import datn.goodboy.model.entity.Styles;
import datn.goodboy.repository.StylesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StylesService {
    @Autowired
    private StylesRepository stylesRepository;

    public Page<Styles> findAll(Pageable pageable) {
        return stylesRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Styles add(Styles origin) {
        return stylesRepository.save(origin);
    }

    public Styles update(Integer id, Styles color) {
        Styles color1 = stylesRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setUpdatedAt(color.getUpdatedAt());
        color1.setStatus(color.getStatus());
        return stylesRepository.save(color1);
    }
}
