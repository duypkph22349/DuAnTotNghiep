package datn.goodboy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.PatternType;
import datn.goodboy.repository.PatternTypeRepository;

@Service
public class PatternTypeService {
    @Autowired
    private PatternTypeRepository patternTypeRepository;

    public Page<PatternType> findAll(Pageable pageable) {
        return patternTypeRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public PatternType add(PatternType origin) {
        return patternTypeRepository.save(origin);
    }

    public PatternType update(Integer id, PatternType color) {
        PatternType color1 = patternTypeRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setUpdatedAt(color.getUpdatedAt());
        color1.setStatus(color.getStatus());
        return patternTypeRepository.save(color1);
    }

    public PatternType getById(Integer id) {
        return patternTypeRepository.findById(id).get();
    }

    public Page<PatternType> searchPatternByKeyword(String keyword, Pageable pageable) {
        return patternTypeRepository.searchByKeyword(keyword, pageable);
    }

    public List<Map<Integer, String>> getCombobox() {
        return patternTypeRepository.getComboBoxMap();
    }

    public void deletePattern(Integer id) {
        Optional<PatternType> patternType = patternTypeRepository.findById(id);
        if (patternType.isPresent()) {
            if (patternType.get().isDeleted()) {
                patternType.get().setDeleted(false);
            } else {
                patternType.get().setDeleted(true);
            }
            patternTypeRepository.save(patternType.get());
        }
    }

    public List<PatternType> getPatternTypeList() {
        return patternTypeRepository.getPatternTypeList();
    }
}
