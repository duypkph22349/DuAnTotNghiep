package datn.goodboy.service;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public Page<ProductDetail> findAllProductDetail(Pageable pageable) {
        return productDetailRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public ProductDetail add(ProductDetail origin) {
        return productDetailRepository.save(origin);
    }

    public ProductDetail update(Integer id, ProductDetail color) {
        ProductDetail color1 = productDetailRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setPrice(color.getPrice());
        color1.setQuantity(color.getQuantity());
        color1.setDescription(color.getDescription());
        color1.setIdProduct(color.getIdProduct());
        color1.setIdPattern(color.getIdPattern());
        color1.setIdColor(color.getIdColor());
        color1.setIdOrigin(color.getIdOrigin());
        color1.setIdBrand(color.getIdBrand());
        color1.setIdMaterial(color.getIdMaterial());
        color1.setIdSize(color.getIdSize());
        color1.setIdStyles(color.getIdStyles());
        color1.setStatus(color.getStatus());
        color1.setUpdatedAt(color.getUpdatedAt());
        return productDetailRepository.save(color1);
    }
}
