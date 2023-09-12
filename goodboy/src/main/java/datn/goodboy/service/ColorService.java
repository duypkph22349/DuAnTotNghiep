package datn.goodboy.service;



import datn.goodboy.model.entity.Color;
import datn.goodboy.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }


    public Page<Color> getPage(Pageable pageable){
        return colorRepository.findAll(pageable);
    }

    public ArrayList<Color> getAll(){
        return (ArrayList<Color>) colorRepository.findAll();
    }


    public Color saveColor(Color color) {

        return colorRepository.save(color);
    }

    public void deleteColor(int id) {
        colorRepository.deleteById(id);
    }

    public Optional<Color> findByIdColor(int id) {
        return colorRepository.findById(id);
    }
}
