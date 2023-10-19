package datn.goodboy.service;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.ProductDetailFilter;
import datn.goodboy.repository.ProductDetailRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;
import datn.goodboy.service.serviceinterface.PanigationWithSearch;
import datn.goodboy.service.serviceinterface.IPanigationWithFIllter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService implements PanigationInterface<ProductDetail>,IPanigationWithFIllter<ProductDetail,ProductDetailFilter>,PanigationWithSearch<ProductDetail> {
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
    // panigation no fillter end
    // panigation no with Seach end
    // panigation no with Seach 
    
    // panigation no with fillter 
  @Override
  public List<ProductDetail> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir,
      ProductDetailFilter filter) {
    // TODO Auto-generated method stub
    if (pageNo > getPageNumber(pageSize,filter) || pageNo < 1) {
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
    Page<ProductDetail> page = productDetailRepository.filter(filter,pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount, ProductDetailFilter filter) {
  Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.filter(filter,pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno, ProductDetailFilter filter) {
Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.filter(filter,pageable); // findAll()
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
    if (pageNo > getPageNumber(pageSize,txtSearch) || pageNo < 1) {
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
    Page<ProductDetail> page = productDetailRepository.searchByText(txtSearch,pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }
  @Override
  public int getPageNumber(int rowcount, String txtSearch) {
     Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.searchByText(txtSearch,pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }
  @Override
  public int[] getPanigation(int rowcount, int pageno, String txtSearch) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<ProductDetail> page = productDetailRepository.searchByText(txtSearch,pageable); // findAll()
    int totalPage = page.getTotalPages();
    return Panigation(pageno, totalPage);
  }
 public int[] Panigation(int pageno, int totalPage){
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
}

