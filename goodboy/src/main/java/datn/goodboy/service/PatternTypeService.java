package datn.goodboy.service;




import datn.goodboy.model.entity.Origin;
import datn.goodboy.model.entity.PatternType;
import datn.goodboy.repository.PatternTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
}