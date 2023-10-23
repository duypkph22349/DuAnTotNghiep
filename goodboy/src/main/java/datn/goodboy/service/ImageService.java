package datn.goodboy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Images;
import datn.goodboy.repository.ImageRepository;

@Service
public class ImageService {
  @Autowired
  ImageRepository repository;

  public List<Images> getAll() {
    return repository.findAll();
  }

  public Images getOne(int id) {
    Optional<Images> images = repository.findById(id);
    if (images.isPresent()) {
      return images.get();
    }
    return null;
  }
}
