package datn.goodboy.service;

import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Origin;
import datn.goodboy.repository.ColorRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public Page<Color> findAllColor(Pageable pageable) {
        return colorRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Color add(Color color) {
        return colorRepository.save(color);
    }

    public Color update(Integer id, Color color) {
        Color color1 = colorRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setUpdatedAt(color.getUpdatedAt());
        color1.setStatus(color.getStatus());
        return colorRepository.save(color1);
    }

    public Color getById(Integer id) {
        return colorRepository.findById(id).get();
    }

    public Page<Color> searchColorByKeyword(String keyword, Pageable pageable) {
        return colorRepository.searchByKeyword(keyword, pageable);
    }

    public List<Map<Integer, String>> getCombobox() {
        return colorRepository.getComboBoxMap();
    }

    public void deleteColor(Integer id) {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            if (color.get().isDeleted()) {
                color.get().setDeleted(false);
            } else {
                color.get().setDeleted(true);
            }
            colorRepository.save(color.get());
        }
    }
}
