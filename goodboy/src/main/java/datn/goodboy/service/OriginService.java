package datn.goodboy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Origin;
import datn.goodboy.repository.OriginRepository;

@Service
public class OriginService {
    @Autowired
    private OriginRepository originRepository;

    public Page<Origin> findAll(Pageable pageable) {
        return originRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Origin add(Origin origin) {
        return originRepository.save(origin);
    }

    public Origin update(Integer id, Origin color) {
        Origin color1 = originRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setUpdatedAt(color.getUpdatedAt());
        color1.setStatus(color.getStatus());
        return originRepository.save(color1);
    }

    public Origin getById(Integer id) {
        return originRepository.findById(id).get();
    }

    public Page<Origin> searchOriginByKeyword(String keyword, Pageable pageable) {
        return originRepository.searchByKeyword(keyword, pageable);
    }

    public List<Map<Integer, String>> getCombobox() {
        return originRepository.getComboBoxMap();
    }

    public void deleteOrigin(Integer id) {
        Optional<Origin> origin = originRepository.findById(id);
        if (origin.isPresent()) {
            if (origin.get().isDeleted()) {
                origin.get().setDeleted(false);
            } else {
                origin.get().setDeleted(true);
            }
            originRepository.save(origin.get());
        }
    }

    public List<Origin> getOriginList() {
        return originRepository.getOriginList();
    }
}
