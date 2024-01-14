package datn.goodboy.service;

import java.util.List;
import java.util.Map;

import datn.goodboy.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Category add(Category category) {
        return categoryRepository.save(category);
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

    public Page<Category> searchCategoryByKeyword(String keyword, Pageable pageable) {
        return categoryRepository.searchByKeyword(keyword, pageable);
    }

    public Category update(Integer id, Category category) {
        Category category1 = categoryRepository.findById(id).get();
        category1.setName(category.getName());
        category1.setUpdatedAt(category.getUpdatedAt());
        category1.setStatus(category.getStatus());
        return categoryRepository.save(category1);
    }
}
