package datn.goodboy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Images;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.ImageRepository;
import datn.goodboy.repository.ProductDetailRepository;

@Service
public class ImageService {
  @Autowired
  ImageRepository repository;
  @Autowired
  ProductDetailRepository productDetailRepository;

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

  public List<Images> saveImageForNewProductDetail(List<String> listurl, int idProdcutDetail) {
    List<Images> listImage = new ArrayList<>();
    Optional<ProductDetail> productdetail = productDetailRepository.findById(idProdcutDetail);
    if (productdetail.isPresent()) {
      ProductDetail productDetail2 = productdetail.get();
      for (String url : listurl) {
        Images img = new Images();
        img.setImg(url);
        img.setIdProductDetail(productDetail2);
        img.setCreatedAt(LocalDateTime.now());
        img.setDeleted(false);
        img.setStatus(1);
        listImage.add(img);
      }
    }
    return repository.saveAll(listImage);
  }

  public Images updateImage(Images images) {
    Images exitsImage = this.getOne(images.getId());
    if (exitsImage != null) {
      exitsImage.setUpdatedAt(LocalDateTime.now());
      exitsImage.setStatus(images.getStatus());
      exitsImage.setImg(images.getImg());
      return repository.save(exitsImage);
    }
    return null;
  }

  public Images deleted(int id) {
    Images exitsImage = this.getOne(id);
    if (exitsImage != null) {
      exitsImage.setUpdatedAt(LocalDateTime.now());
      exitsImage.setDeleted(true);
      return repository.save(exitsImage);
    }
    return null;
  }

  public List<Images> getAllImageInProductDetail(int id) {
    return repository.getAllImageInProductDetail(id);
  }
}
