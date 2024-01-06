package datn.goodboy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Category;
import datn.goodboy.model.entity.Material;
import datn.goodboy.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public List<Category> getAllCartDetail() {
        return categoryRepository.getCategoryAble();
    }

    public Category add(Category entity) {
        return categoryRepository.save(entity);
    }

    public List<Category> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    public Category getById(int idCategory) {
        return categoryRepository.findById(idCategory).get();
    }

    public List<Map<Integer, String>> getCombobox() {
        return categoryRepository.getComboBoxMap();
    }
}
