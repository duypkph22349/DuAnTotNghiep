package datn.goodboy.service.test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Product;
import datn.goodboy.model.request.ProductFilter;
import datn.goodboy.repository.ProductRepository;
import datn.goodboy.service.serviceinterface.IPanigationWithFIllter;
import datn.goodboy.service.serviceinterface.PanigationInterface;

@Service("testProductService")
public class ProductService implements PanigationInterface<Product>, IPanigationWithFIllter<Product, ProductFilter> {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> getNewProducts(int pageSize) {
    Sort sort = Sort.by("createdAt").ascending();
    return productRepository.getProductSales(PageRequest.of(0, pageSize, sort)).getContent();
  }

  public List<Product> getTopProductSaleThisYear(int pageSize) {
    return productRepository
        .findAllById(productRepository.getTopProductsSale(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
            LocalDate.now().atTime(23, 59, 59), PageRequest.of(0, pageSize)).getContent().stream().map(Long::intValue)
            .collect(Collectors.toList()));
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
        return productRepository.filterAll(filter).stream()
            .sorted(Comparator.comparing(Product::getMinPrice))
            .skip((pageNo - 1) * pageSize) // Skip items on previous pages
            .limit(pageSize) // Limit the number of items per page
            .collect(Collectors.toList());
      } else {
        return productRepository.filterAll(filter).stream()
            .sorted(Comparator.comparing(Product::getMaxPrice).reversed())
            .skip((pageNo - 1) * pageSize) // Skip items on previous pages
            .limit(pageSize) // Limit the number of items per page
            .collect(Collectors.toList());
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
    return IPanigationWithFIllter.Panigation(pageno, totalPage);
  }

  @Override
  public List<Product> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > getPageNumber(pageSize) || pageNo < 1) {
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
      Page<Product> page = productRepository.getProductSales(pageable);
      return page.getContent();
    } else {
      if (sortDir) {
        return productRepository.getProductSales().stream()
            .sorted(Comparator.comparing(Product::getMinPrice))
            .skip((pageNo - 1) * pageSize) // Skip items on previous pages
            .limit(pageSize) // Limit the number of items per page
            .collect(Collectors.toList());
      } else {
        return productRepository.getProductSales().stream()
            .sorted(Comparator.comparing(Product::getMaxPrice))
            .skip((pageNo - 1) * pageSize) // Skip items on previous pages
            .limit(pageSize) // Limit the number of items per page
            .collect(Collectors.toList());
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
    return IPanigationWithFIllter.Panigation(pageno, totalPage);
  }

  public Product getById(Integer id) {
    return productRepository.findById(id).get();
  }

}
