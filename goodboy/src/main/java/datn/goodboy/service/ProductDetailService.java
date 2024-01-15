package datn.goodboy.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import datn.goodboy.exeption.rest.ErrorCreateBill;

import datn.goodboy.model.entity.PatternType;
import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.entity.Size;
import datn.goodboy.model.request.ProductDetailFilter;
import datn.goodboy.model.request.ProductDetailRequest;
import datn.goodboy.repository.ProductDetailRepository;
import datn.goodboy.service.cloud.CloudinaryImageService;
import datn.goodboy.service.serviceinterface.IPanigationWithFIllter;
import datn.goodboy.service.serviceinterface.PanigationInterface;
import datn.goodboy.service.serviceinterface.PanigationWithSearch;

@Service
public class ProductDetailService implements PanigationInterface<ProductDetail>,
        IPanigationWithFIllter<ProductDetail, ProductDetailFilter>, PanigationWithSearch<ProductDetail> {

  @Autowired
  private PatternTypeService patternTypeService;

  @Autowired
  private ProductService productService;

  @Autowired
  private SizeService sizeService;

  @Autowired
  private ImageService imageService;
  @Autowired
  private ProductDetailRepository productDetailRepository;

  @Autowired
  CloudinaryImageService cloudService;

  public ProductDetailRequest getUpdateProductDetailForm(Integer id) {

    return null;
  }

  public void updateStatus(int id) {
    Optional<ProductDetail> pOptional = productDetailRepository.findById(id);
    if (pOptional.isPresent()) {
      if (pOptional.get().getStatus() == 0) {
        pOptional.get().setStatus(1);
      } else if (pOptional.get().getStatus() == 1) {
        pOptional.get().setStatus(0);
      }
      productDetailRepository.save(pOptional.get());
    }
  }

  public ProductDetail saveProdudct(ProductDetailRequest request, List<MultipartFile> listImage) throws IOException {
    ProductDetail productDetail = new ProductDetail();
    mapRequestToEntity(request, productDetail);
    productDetail.setCreatedAt(LocalDateTime.now());
    productDetail.setId(null);
    ProductDetail savDetail = productDetailRepository.save(productDetail);
    int idProduct = savDetail.getId();
    CompletableFuture.runAsync(() -> {
      List<String> listURL = new ArrayList<>();
      if (!listImage.isEmpty()) {
        for (MultipartFile multipartFile : listImage) {
          try {
            listURL.add(cloudService.saveImage(multipartFile));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        imageService.saveImageForNewProductDetail(listURL, idProduct);
      }
    });
    return savDetail;
  }

  public ProductDetail updateProductDetail(ProductDetailRequest request, List<MultipartFile> listImage)
          throws IOException {
    Optional<ProductDetail> productDetail = productDetailRepository.findById(request.getId());
    if (productDetail.isPresent()) {
      ProductDetail exitproductDetail = productDetail.get();
      mapRequestToEntity(request, exitproductDetail);
      exitproductDetail.setUpdatedAt(LocalDateTime.now());
      ProductDetail savDetail = productDetailRepository.save(exitproductDetail);
      int idProduct = savDetail.getId();
      CompletableFuture.runAsync(() -> {
        List<String> listURL = new ArrayList<>();
        if (!listImage.isEmpty()) {
          for (MultipartFile multipartFile : listImage) {
            try {
              listURL.add(cloudService.saveImage(multipartFile));
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          imageService.saveImageForNewProductDetail(listURL, idProduct);
        }
      });
      return savDetail;
    } else {
      // throws exeption
      return null;
    }
  }

  public ProductDetail saveProductDetail(ProductDetailRequest request) {
    ProductDetail productDetail = new ProductDetail();
    mapRequestToEntity(request, productDetail);
    productDetail.setCreatedAt(LocalDateTime.now());
    productDetail.setId(null);
    if (productDetail.getIdProduct().exitSizes(productDetail.getIdSize().getId(),
            productDetail.getIdPattern().getId())) {
      throw new RuntimeException("Kích thước này đã tồn tại");
    }
    return productDetailRepository.save(productDetail);
  }

  public void saveImage(int idProduct, List<MultipartFile> listImage) {
    List<String> listURL = new ArrayList<>();
    if (!listImage.isEmpty()) {
      for (MultipartFile multipartFile : listImage) {
        try {
          listURL.add(cloudService.saveImage(multipartFile));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      imageService.saveImageForNewProductDetail(listURL, idProduct);
    }
  }

  public ProductDetailRequest getProductDetailRequetById(Integer id) {
    ProductDetailRequest request = new ProductDetailRequest();
    Optional<ProductDetail> productDetail = productDetailRepository.findById(id);
    if (productDetail.isPresent()) {
      mapEntitytoRequest(productDetail.get(), request);
    } else {
      // thowre exeption
    }
    return request;
  }

  public void mapEntitytoRequest(ProductDetail productDetail, ProductDetailRequest productDetailRequest) {
    productDetailRequest.setId(productDetail.getId());
    productDetailRequest.setDescription(productDetail.getDescription());
    productDetailRequest.setDeleted(productDetail.isDeleted());
    if (productDetail.getIdPattern() != null) {
      productDetailRequest.setIdPattern(productDetail.getIdPattern().getId());
    }
    if (productDetail.getIdProduct() != null) {
      productDetailRequest.setIdProduct(productDetail.getIdProduct().getId());
    }
    if (productDetail.getIdSize() != null) {
      productDetailRequest.setIdSize(productDetail.getIdSize().getId());
    }
    productDetailRequest.setQuantity(productDetail.getQuantity());
    productDetailRequest.setPrice(productDetail.getPrice());
    productDetailRequest.setStatus(productDetail.getStatus());
    productDetailRequest.setName(productDetail.getName());
    productDetailRequest.setImage(productDetail.getImageProducts());
  }

  public void mapRequestToEntity(ProductDetailRequest request, ProductDetail entity) {
    PatternType pattern = patternTypeService.getById(request.getIdPattern());
    Product product = productService.getById(request.getIdProduct());
    Size size = sizeService.getById(request.getIdSize());
    entity.setIdPattern(pattern);
    entity.setIdProduct(product);
    entity.setIdSize(size);
    // entity.setDeleted(request.isDeleted());
    entity.setQuantity(request.getQuantity());
    entity.setName(request.getName());
    entity.setDescription(request.getDescription());
    entity.setPrice(request.getPrice());
    entity.setStatus(request.getStatus());
    entity.setId(request.getId());
    System.out.println(request);
  }

  public Page<ProductDetail> findAllProductDetail(Pageable pageable) {
    return productDetailRepository.findAllByOrderByCreatedAtDesc(pageable);
  }

  public ProductDetail add(ProductDetail entity) {
    return productDetailRepository.save(entity);
  }

  public ProductDetail add(ProductDetailRequest entity) {
    return null;
  }

  public ProductDetail update(Integer id, ProductDetail request) {
    ProductDetail productdetail = productDetailRepository.findById(id).get();
    productdetail.setName(request.getName());
    productdetail.setPrice(request.getPrice());
    productdetail.setQuantity(request.getQuantity());
    productdetail.setDescription(request.getDescription());
    productdetail.setIdProduct(request.getIdProduct());
    productdetail.setIdPattern(request.getIdPattern());
    productdetail.setIdSize(request.getIdSize());
    productdetail.setStatus(request.getStatus());
    productdetail.setUpdatedAt(request.getUpdatedAt());
    return productDetailRepository.save(productdetail);
  }

  // panigation no fillter
  @Override
  public List<ProductDetail> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > getPageNumber(pageSize) || pageNo < 1) {
      return null;
    }
    List<ProductDetail> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    Page<ProductDetail> page = productDetailRepository.findAll(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.findAll(pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.findAll(pageable); // findAll()
    int totalPage = page.getTotalPages();
    return Panigation(pageno, totalPage);
  }

  @Override
  public List<ProductDetail> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir,
                                       ProductDetailFilter filter) {
    if (pageNo > getPageNumber(pageSize, filter) || pageNo < 1) {
      return null;
    }
    List<ProductDetail> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    Page<ProductDetail> page = productDetailRepository.filter(filter, pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount, ProductDetailFilter filter) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.filter(filter, pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno, ProductDetailFilter filter) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.filter(filter, pageable); // findAll()
    int totalPage = page.getTotalPages();
    return Panigation(pageno, totalPage);
  }
  // panigation no with fillter end

  @Override
  public List<ProductDetail> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir, String txtSearch) {
    // TODO Auto-generated method stub
    if (pageNo > getPageNumber(pageSize, txtSearch) || pageNo < 1) {
      return null;
    }
    List<ProductDetail> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    // findAll method and pass pageable instance
    Page<ProductDetail> page = productDetailRepository.searchByText(txtSearch, pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount, String txtSearch) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.searchByText(txtSearch, pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno, String txtSearch) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.searchByText(txtSearch, pageable); // findAll()
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

  public ArrayList<ProductDetail> getAllProductDetail() {
    return (ArrayList<ProductDetail>) productDetailRepository.findAll();
  }

  public Optional<ProductDetail> getProductDetailById(Integer id) {
    return productDetailRepository.findById(id);
  }

  public Optional<ProductDetail> getProductByLong(Long id) {
    return productDetailRepository.getProductByLongId(id);
  }

  public boolean enoughtProduct(int productId, int quantity) {
    Optional<ProductDetail> productDetail = this.getProductDetailById(productId);
    if (productDetail.isPresent()) {
      return (productDetail.get().getQuantity() > quantity);
    } else {
      throw new ErrorCreateBill("saản phẩm không tồn tại");
    }
  }

  public void saleProduct(int productId, int quantity) {
    Optional<ProductDetail> productDetail = this.getProductDetailById(productId);
    if (productDetail.isPresent()) {
      ProductDetail exitProductDetail = productDetail.get();
      if (exitProductDetail.getQuantity() < quantity) {
        throw new ErrorCreateBill("Số lượng của sản phẩm: " + productDetail.get().getName() // throw another exeption
                + " không đủ, hiện chỉ còn lại " + exitProductDetail.getQuantity() + " sản phẩm");
      } else {
        exitProductDetail.setQuantity(exitProductDetail.getQuantity() - quantity);
        productDetailRepository.save(exitProductDetail);
      }
    }
  }

  public void saleProductInUser(int productId, int quantity) {
    Optional<ProductDetail> productDetail = this.getProductDetailById(productId);
    if (productDetail.isPresent()) {
      ProductDetail exitProductDetail = productDetail.get();
      if (exitProductDetail.getQuantity() < quantity) {
        throw new ErrorCreateBill("Số lượng của sản phẩm: " + productDetail.get().getName() // throw another exeption
                + " không đủ, hiện chỉ còn lại " + exitProductDetail.getQuantity() + " sản phẩm");
      } else {
        exitProductDetail.setQuantity(exitProductDetail.getQuantity() - quantity);
        productDetailRepository.save(exitProductDetail);
      }
    }
  }
  // public void updateProductQuantities(List<BillDetail> billDetails) {
  // for (BillDetail billDetail : billDetails) {
  // ProductDetail productDetail = billDetail.getProductDetail();
  // int quantitySold = billDetail.getQuantity();
  //
  // // Truy xuất số lượng hiện tại của sản phẩm
  // int currentQuantity = productDetail.getQuantity();
  //
  // // Giảm đi số lượng đã bán
  // int updatedQuantity = currentQuantity - quantitySold;
  //
  // // Cập nhật số lượng sản phẩm
  // productDetail.setQuantity(updatedQuantity);
  //
  // // Lưu thông tin sản phẩm đã cập nhật vào cơ sở dữ liệu
  // productDetailRepository.save(productDetail);
  // }
  // }
}
