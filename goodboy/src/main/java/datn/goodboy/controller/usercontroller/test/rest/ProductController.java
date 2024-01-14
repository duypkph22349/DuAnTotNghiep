package datn.goodboy.controller.usercontroller.test.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.ProductFilter;
import datn.goodboy.service.test.ProductService;

@RestController("testRestProductController")
@RequestMapping("/product/test/rest")

public class ProductController {
  @Autowired
  private ProductService productService; // Assuming ProductService has the getPageNo method

  @PostMapping(value = "/getProducts")
  public List<Product> getPageProducts(@RequestBody ProductFilter filterRequest) {
    // Assuming ProductService has a method getPageNo that retrieves the list
    System.out.println(filterRequest);
    return productService.getPageNo(filterRequest.getPageno(), filterRequest.getRowcount(), filterRequest.getSortBy(),
        filterRequest.isSortDir(), filterRequest);
  }

  @PostMapping(value = "/getPanigation")
  public int[] getPanigation(@RequestBody ProductFilter filterRequest) {
    return productService.getPanigation(filterRequest.getRowcount(), filterRequest.getPageno(), filterRequest);
  }

  @PostMapping(value = "/getTotalPage")
  public int getTotalPage(@RequestBody ProductFilter filterRequest) {
    return productService.getPageNumber(filterRequest.getRowcount(), filterRequest);
  }

  @GetMapping(value = "/detail/{idproduct}")
  public ProductDetail getProductDetail(@PathVariable("idproduct") int idProduct) {
    return productService.getProductDetails(idProduct);
  }
}
