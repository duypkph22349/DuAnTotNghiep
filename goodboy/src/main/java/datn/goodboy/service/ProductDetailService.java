package datn.goodboy.service;

import datn.goodboy.exeption.rest.ErrorCreateBill;
import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Material;
import datn.goodboy.model.entity.Origin;
import datn.goodboy.model.entity.PatternType;
import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.entity.Size;
import datn.goodboy.model.entity.Styles;
import datn.goodboy.model.request.ProductDetailFilter;
import datn.goodboy.model.request.ProductDetailRequest;
import datn.goodboy.repository.ProductDetailRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;
import datn.goodboy.service.serviceinterface.PanigationWithSearch;
import datn.goodboy.service.cloud.CloudinaryImageService;
import datn.goodboy.service.serviceinterface.IPanigationWithFIllter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService implements PanigationInterface<ProductDetail>,
    IPanigationWithFIllter<ProductDetail, ProductDetailFilter>, PanigationWithSearch<ProductDetail> {

  @Autowired
  private BrandService brandService;

  @Autowired
  private ColorService colorService;

  @Autowired
  private MaterialService materialService;

  @Autowired
  private OriginService originService;

  @Autowired
  private PatternTypeService patternTypeService;

  @Autowired
  private ProductService productService;

  @Autowired
  private SizeService sizeService;

  @Autowired
  private StylesService stylesService;

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
    ProductDetail savDetail = productDetailRepository.save(productDetail);
    int idProduct = savDetail.getId();
    List<String> listURL = new ArrayList<>();
    for (MultipartFile multipartFile : listImage) {
      String url = cloudService.saveImage(multipartFile);
      listURL.add(url);
    }
    imageService.saveImageForNewProductDetail(listURL, idProduct);
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
      List<String> listURL = new ArrayList<>();
      if (listImage.isEmpty() | listImage == null) {
      } else {
        for (MultipartFile multipartFile : listImage) {
          if (!multipartFile.isEmpty()) {
            String url = cloudService.saveImage(multipartFile);
            listURL.add(url);
          } else {
            // thows exeption
          }
        }
        imageService.saveImageForNewProductDetail(listURL, idProduct);
      }
      return savDetail;
    } else {
      // throws exeption
      return null;
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
    productDetailRequest.setIdBrand(productDetail.getIdBrand().getId());
    productDetailRequest.setIdMaterial(productDetail.getIdMaterial().getId());
    productDetailRequest.setIdColor(productDetail.getIdColor().getId());
    productDetailRequest.setIdOrigin(productDetail.getIdOrigin().getId());
    productDetailRequest.setIdPattern(productDetail.getIdPattern().getId());
    productDetailRequest.setIdProduct(productDetail.getIdProduct().getId());
    productDetailRequest.setQuantity(productDetail.getQuantity());
    productDetailRequest.setPrice(productDetail.getPrice());
    productDetailRequest.setStatus(productDetail.getQuantity());
    productDetailRequest.setName(productDetail.getName());
    productDetailRequest.setImage(productDetail.getImageProducts());
  }

  public void mapRequestToEntity(ProductDetailRequest request, ProductDetail entity) {
    Brand brand = brandService.getById(request.getIdBrand());
    Color color = colorService.getById(request.getIdColor());
    Material material = materialService.getById(request.getIdMaterial());
    Origin origin = originService.getById(request.getIdOrigin());
    PatternType pattern = patternTypeService.getById(request.getIdPattern());
    Product product = productService.getById(request.getIdProduct());
    Size size = sizeService.getById(request.getIdSize());
    Styles styles = stylesService.getById(request.getIdStyles());
    entity.setIdBrand(brand);
    entity.setIdColor(color);
    entity.setIdMaterial(material);
    entity.setIdOrigin(origin);
    entity.setIdPattern(pattern);
    entity.setIdProduct(product);
    entity.setIdSize(size);
    entity.setIdStyles(styles);
    entity.setDeleted(request.isDeleted());
    entity.setName(request.getName());
    entity.setPrice(request.getPrice());
    entity.setStatus(request.getStatus());
    entity.setId(request.getId());
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
    // findAll method and pass pageable instance
    Page<ProductDetail> page = productDetailRepository.findAll(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.findAll(pageable);
    int totalPage = page.getTotalPages();
    // if(totalPage <=1){
    // totalPage =1;
    // }
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.findAll(pageable); // findAll()
    int totalPage = page.getTotalPages();
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
  // panigation no fillter end
  // panigation no with Seach end
  // panigation no with Seach

  // panigation no with fillter
  @Override
  public List<ProductDetail> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir,
      ProductDetailFilter filter) {
    // TODO Auto-generated method stub
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
    // findAll method and pass pageable instance
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

  public boolean enoughtProduct(int productId, int quantity) {
    Optional<ProductDetail> productDetail = this.getProductDetailById(productId);
    if (productDetail.isPresent()) {
      return (productDetail.get().getQuantity() > quantity);
    } else {
      throw new ErrorCreateBill("saản phẩm không tồn tại");
    }
  }

  public void saleProduct(int productId, int quantity) {
    System.out.println("idprc " + productId + " quantity " + quantity);
    Optional<ProductDetail> productDetail = this.getProductDetailById(productId);
    if (productDetail.isPresent()) {
      ProductDetail exitProductDetail = productDetail.get();
      if (exitProductDetail.getQuantity() < quantity) {
        System.out.println(" Khong du so luong");
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
}
