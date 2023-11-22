package datn.goodboy.service;

import datn.goodboy.model.entity.ImageProduct;
import datn.goodboy.model.entity.Images;
import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.ImageProductRepository;
import datn.goodboy.repository.ImageRepository;
import datn.goodboy.repository.ProductDetailRepository;
import datn.goodboy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageProductService {
    @Autowired
    ImageProductRepository repository;
    @Autowired
    ProductRepository productRepository;

    public List<ImageProduct> getAll(){
        return repository.findAll();
    }

    public ImageProduct getOne(int id) {
        Optional<ImageProduct> images = repository.findById(id);
        if (images.isPresent()) {
            return images.get();
        }
        return null;
    }

    public List<ImageProduct> saveImageForNewProductDetail(List<String> listurl, int idProdcutDetail) {
        List<ImageProduct> listImage = new ArrayList<>();
        Optional<Product> productdetail = productRepository.findById(idProdcutDetail);
        if (productdetail.isPresent()) {
            Product productDetail2 = productdetail.get();
            for (String url : listurl) {
                ImageProduct img = new ImageProduct();
                img.setImg(url);
                img.setIdProduct(productDetail2);
                img.setCreatedAt(LocalDateTime.now());
                img.setDeleted(false);
                img.setStatus(1);
                listImage.add(img);
            }
        }
        return repository.saveAll(listImage);
    }
}
