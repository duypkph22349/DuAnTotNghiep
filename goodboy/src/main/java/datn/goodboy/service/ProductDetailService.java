package datn.goodboy.service;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.response.ProductDetailResponse;
import datn.goodboy.repository.ProductDetailRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService implements PanigationInterface<ProductDetail> {
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
@Override
  public List<ProductDetail> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > getPageNumber(pageSize)) {
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
    //   totalPage =1;
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

    // panigation start
  // panigation
  // @Override
  // public List<ProductDetailResponse> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
  //   if (pageNo > getPageNumber(pageSize)) {
  //     return null;
  //   }
  //   List<ProductDetailResponse> ChiTietSanPhams;
  //   ChiTietSanPhams = new ArrayList<>();
  //   Sort sort;
  //   if (sortDir) {
  //     sort = Sort.by(sortBy).ascending();
  //   } else {
  //     sort = Sort.by(sortBy).descending();
  //   }
  //   Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
  //   // findAll method and pass pageable instance
  //   Page<ProductDetailResponse> page = productDetailRepository.getResponsePage(pageable);
  //   ChiTietSanPhams = page.getContent();
  //   return ChiTietSanPhams;
  // }

  // @Override
  // public int getPageNumber(int rowcount) {
  //   Pageable pageable = PageRequest.of(0, rowcount);
  //   Page<ProductDetailResponse> page = productDetailRepository.getResponsePage(pageable);
  //   int totalPage = page.getTotalPages();
  //   // if(totalPage <=1){
  //   //   totalPage =1;
  //   // }
  //   return totalPage;
  // }

  // @Override
  // public int[] getPanigation(int rowcount, int pageno) {
  //   Pageable pageable = PageRequest.of(0, rowcount);
  //   Page<ProductDetailResponse> page = productDetailRepository.getResponsePage(pageable); // findAll()
  //   int totalPage = page.getTotalPages();
  //   int[] rs;
  //   if (totalPage <= 1) {
  //     int[] rs1 = { 1 };
  //     return rs1;
  //   } else if (totalPage <= 3) {
  //     rs = new int[totalPage];
  //     for (int i = 1; i <= totalPage; i++) {
  //       rs[i - 1] = i;
  //     }
  //     return rs;
  //   } else {
  //     rs = new int[3];
  //     if (pageno <= 2) {
  //       int[] rs1 = { 1, 2, 3 };
  //       rs = rs1;
  //     }
  //     if (pageno > 2) {
  //       if (pageno < totalPage - 1) {
  //         int[] rs1 = { pageno - 1, pageno, pageno + 1 };
  //         rs = rs1;
  //       }
  //       if (pageno >= totalPage - 1) {
  //         int[] rs1 = { totalPage - 2, totalPage - 1, totalPage };
  //         rs = rs1;
  //       }
  //     }
  //     return rs;
  //   }
  // }
}
