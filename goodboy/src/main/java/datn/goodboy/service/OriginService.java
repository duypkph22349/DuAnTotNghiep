package datn.goodboy.service;


import datn.goodboy.model.entity.Origin;
import datn.goodboy.repository.OriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
}
