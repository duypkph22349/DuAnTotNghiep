package datn.goodboy.service;




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

    @Autowired
    public PatternTypeService(PatternTypeRepository patternTypeRepository) {
        this.patternTypeRepository = patternTypeRepository;
    }


    public Page<PatternType> getPage(Pageable pageable){
        return patternTypeRepository.findAll(pageable);
    }

    public ArrayList<PatternType> getAll(){
        return (ArrayList<PatternType>) patternTypeRepository.findAll();
    }


    public PatternType savePattern(PatternType patternType) {

        return patternTypeRepository.save(patternType);
    }

    public void deletePattern(int id) {
        patternTypeRepository.deleteById(id);
    }

    public Optional<PatternType> findByIdPattern(int id) {
        return patternTypeRepository.findById(id);
    }
}
