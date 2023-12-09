package datn.goodboy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Category;
import datn.goodboy.model.entity.Material;
import datn.goodboy.model.entity.Origin;
import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.Styles;
import datn.goodboy.model.request.ProductFilter;
import datn.goodboy.model.request.ProductRequest;
import datn.goodboy.repository.BrandRepository;
import datn.goodboy.repository.CategoryRepository;
import datn.goodboy.repository.MaterialRepository;
import datn.goodboy.repository.OriginRepository;
import datn.goodboy.repository.ProductRepository;
import datn.goodboy.repository.StylesRepository;
import datn.goodboy.service.cloud.CloudinaryImageService;
import datn.goodboy.service.serviceinterface.IPanigationWithFIllter;
import datn.goodboy.service.serviceinterface.PanigationInterface;

@Service
public class ProductService implements PanigationInterface<Product>, IPanigationWithFIllter<Product, ProductFilter> {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private StylesRepository stylesRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired

    private MaterialRepository metarialRepository;
    @Autowired
    CloudinaryImageService cloudService;
    @Autowired
    ImageProductService imageService;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Product add(Product origin) {
        return productRepository.save(origin);
    }

    public Product update(Integer id, Product color) {
        Product color1 = productRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setUpdatedAt(color.getUpdatedAt());
        color1.setStatus(color.getStatus());
        return productRepository.save(color1);
    }

    public void requestToEntity(ProductRequest request, Product entity) {
        Optional<Brand> braOptional = brandRepository.findById(request.getIdBrand());
        Optional<Origin> orgOptional = originRepository.findById(request.getIdOrigin());
        Optional<Styles> stysOptional = stylesRepository.findById(request.getIdStyles());
        Optional<Category> cateOptional = categoryRepository.findById(request.getIdCategory());
        Optional<Material> meOptional = metarialRepository.findById(request.getIdMaterial());
        entity.setIdBrand(braOptional.get());
        entity.setIdOrigin(orgOptional.get());
        entity.setIdMaterial(meOptional.get());
        entity.setIdCategory(cateOptional.get());
        entity.setIdStyles(stysOptional.get());
        entity.setStatus(entity.getStatus());
        entity.setName(entity.getName());
        request.setId(entity.getId());
    }

    public void entityToRequest(Product entity, ProductRequest request) {
        request.setImages(entity.getImageProducts());
        request.setIdBrand(entity.getIdBrand() != null ? entity.getIdBrand().getId() : null);
        request.setIdOrigin(entity.getIdOrigin() != null ? entity.getIdOrigin().getId() : null);
        request.setIdMaterial(entity.getIdBrand() != null ? entity.getIdBrand().getId() : null);
        request.setIdCategory(entity.getIdCategory() != null ? entity.getIdCategory().getId() : null);
        request.setIdStyles(entity.getIdStyles() != null ? entity.getIdStyles().getId() : null);
        request.setStatus(entity.getStatus());
        request.setName(entity.getName());
        request.setId(entity.getId());

    }

    public ProductRequest getRequest(int id) {
        ProductRequest request = new ProductRequest();
        Product productExits = this.getById(id);
        entityToRequest(productExits, request);
        return request;
    }

    public Product updateProduct(ProductRequest request, List<MultipartFile> listimage) {
        Product productExits = this.getById(request.getId());
        requestToEntity(request, productExits);
        CompletableFuture.runAsync(() -> {
            List<String> listURL = new ArrayList<>();
            if (!listimage.isEmpty()) {
                for (MultipartFile multipartFile : listimage) {
                    try {
                        listURL.add(cloudService.saveImage(multipartFile));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                imageService.saveImageForNewProductDetail(listURL, request.getId());
            }
        });
        return productRepository.save(productExits);
    }

    public Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    public Page<Product> searchProductByKeyword(String keyword, Pageable pageable) {
        return productRepository.searchByKeyword(keyword, pageable);
    }

    public List<Map<Integer, String>> getCombobox() {
        return productRepository.getComboBoxMap();
    }

    public void deleteProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            if (product.get().isDeleted()) {
                product.get().setDeleted(false);
            } else {
                product.get().setDeleted(true);
            }
            productRepository.save(product.get());
        }
    }

    @Override
    public List<Product> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
        if (!sortBy.equals("price")) {
            if (pageNo > getPageNumber(pageSize)) {
                return null;
            }
            Sort sort;
            if (sortDir) {
                sort = Sort.by(sortBy).ascending();
            } else {
                sort = Sort.by(sortBy).descending();
            }
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
            Page<Product> page = productRepository.getProductSales(pageable);
            return page.getContent();
        } else {
            if (pageNo > getPageNumber(pageSize)) {
                return null;
            }
            if (sortDir) {
                Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
                Page<Product> page = productRepository.getProductSalesByPriceAsc(pageable);
                return page.getContent();
            } else {
                Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
                Page<Product> page = productRepository.getProductSalesByPriceDesc(pageable);
                return page.getContent();
            }
        }
    }

    @Override
    public int getPageNumber(int rowcount) {
        Pageable pageable = PageRequest.of(0, rowcount);
        Page<Product> page = productRepository.getProductSales(pageable);
        int totalPage = page.getTotalPages();
        return totalPage;
    }

    @Override
    public int[] getPanigation(int rowcount, int pageno) {
        Pageable pageable = PageRequest.of(0, rowcount);
        Page<Product> page = productRepository.getProductSales(pageable);
        int totalPage = page.getTotalPages();
        return Panigation(pageno, totalPage);
    }

    public int[] Panigation(int pageno, int totalPage) {
        int[] rs;
        if (totalPage <= 1) {
            int[] rs1 = { 1 };
            return rs1;
        } else if (totalPage <= 3) {
            rs = new int[totalPage];
            for (int i = 1; i <= totalPage; i++) {
                rs[i - 1] = i;
            }
            return rs;
        } else {
            rs = new int[3];
            if (pageno <= 2) {
                int[] rs1 = { 1, 2, 3 };
                rs = rs1;
            }
            if (pageno > 2) {
                if (pageno < totalPage - 1) {
                    int[] rs1 = { pageno - 1, pageno, pageno + 1 };
                    rs = rs1;
                }
                if (pageno >= totalPage - 1) {
                    int[] rs1 = { totalPage - 2, totalPage - 1, totalPage };
                    rs = rs1;
                }
            }
            return rs;
        }
    }

    @Override
    public List<Product> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir, ProductFilter filter) {
        if (pageNo > getPageNumber(pageSize, filter) || pageNo < 1) {
            return null;
        }
        if (!sortBy.equals("price")) {
            Sort sort;
            if (sortDir) {
                sort = Sort.by(sortBy).ascending();
            } else {
                sort = Sort.by(sortBy).descending();
            }
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
            Page<Product> page = productRepository.filter(filter, pageable);
            return page.getContent();
        } else {
            if (sortDir) {
                Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
                Page<Product> page = productRepository.filter(filter, pageable);
                return page.getContent();
            } else {
                Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
                Page<Product> page = productRepository.filter(filter, pageable);
                return page.getContent();
            }
        }
    }

    @Override
    public int getPageNumber(int rowcount, ProductFilter filter) {
        Pageable pageable = PageRequest.of(0, rowcount);
        Page<Product> page = productRepository.filter(filter, pageable);
        int totalPage = page.getTotalPages();
        return totalPage;
    }

    @Override
    public int[] getPanigation(int rowcount, int pageno, ProductFilter filter) {
        Pageable pageable = PageRequest.of(0, rowcount);
        Page<Product> page = productRepository.filter(filter, pageable); // findAll()
        int totalPage = page.getTotalPages();
        return Panigation(pageno, totalPage);
    }

}
