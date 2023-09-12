package datn.goodboy.service;

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

    @Autowired
    public StylesService(StylesRepository stylesRepository) {
        this.stylesRepository = stylesRepository;
    }


    public Page<Styles> getPage(Pageable pageable){
        return stylesRepository.findAll(pageable);
    }

    public ArrayList<Styles> getAll(){
        return (ArrayList<Styles>) stylesRepository.findAll();
    }


    public Styles saveStyles(Styles styles) {

        return stylesRepository.save(styles);
    }

    public void deleteStyles(int id) {
        stylesRepository.deleteById(id);
    }

    public Optional<Styles> findByIdStyles(int id) {
        return stylesRepository.findById(id);
    }
}
