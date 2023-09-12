package datn.goodboy.service;



import datn.goodboy.model.entity.Origin;
import datn.goodboy.repository.OriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OriginService {
    @Autowired
    private OriginRepository originRepository;

    @Autowired
    public OriginService(OriginRepository originRepository) {
        this.originRepository = originRepository;
    }


    public Page<Origin> getPage(Pageable pageable){
        return originRepository.findAll(pageable);
    }

    public ArrayList<Origin> getAll(){
        return (ArrayList<Origin>) originRepository.findAll();
    }


    public Origin saveOrigin(Origin origin) {

        return originRepository.save(origin);
    }

    public void deleteOrigin(int id) {
        originRepository.deleteById(id);
    }

    public Optional<Origin> findByIdOrigin(int id) {
        return originRepository.findById(id);
    }
}
