package datn.goodboy.controller.testcontroller;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.ProductDetailFilter;
import datn.goodboy.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khanchoang/productdetail")
public class RestProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<ProductDetail>> hienThi(@RequestParam(name = "pageno", defaultValue = "1") int page) {
        return ResponseEntity.ok().body(productDetailService.getPageNo(page, 5, "code", true));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductDetail>> getFilter(@RequestBody ProductDetailFilter filter,
            @RequestParam(name = "pageno", defaultValue = "1") int page) {
        return ResponseEntity.ok().body(productDetailService.getPageNo(page, 5, "code", true, filter));
    }

    @GetMapping("search")
    public ResponseEntity<List<ProductDetail>> search(
            @RequestParam(name = "search", defaultValue = "") String txtS,
            @RequestParam(name = "pageno", defaultValue = "1") int page) {
        return ResponseEntity.ok().body(productDetailService.getPageNo(page, 5, "code", true, txtS));
    }
}
