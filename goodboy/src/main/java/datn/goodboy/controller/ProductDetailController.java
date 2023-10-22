package datn.goodboy.controller;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/khan-choang/productdetail")
public class ProductDetailController {
//    @Autowired
//    private ProductDetailService productDetailService;
//    private int currentProductCode = 1;
//    @GetMapping("/hien-thi")
//    public ResponseEntity<Page<ProductDetail>> hienThi(@RequestParam(defaultValue = "1") int page) {
//        Pageable pageable = PageRequest.of(page - 1, 5);
//        return ResponseEntity.ok().body(productDetailService.findAllProductDetail(pageable));
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<?> add( @RequestBody ProductDetail b, BindingResult result) {
//        if (result.hasErrors()) {
//            List<ObjectError> objectErrorList = result.getAllErrors();
//            return ResponseEntity.ok(objectErrorList);
//        }
//        String newProductCode = "PD" + String.format("%d", currentProductCode);
//        b.setCode(newProductCode);
//        b.setCreatedAt(LocalDateTime.now());
//        b.setUpdatedAt(LocalDateTime.now());
//        b.setStatus(1);
//        currentProductCode++;
//        return ResponseEntity.ok(productDetailService.add(b));
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update( @RequestBody ProductDetail b, @PathVariable Integer id, BindingResult result) {
//        if (result.hasErrors()) {
//            List<ObjectError> objectErrorList = result.getAllErrors();
//            return ResponseEntity.ok(objectErrorList);
//        }
//        b.setUpdatedAt(LocalDateTime.now());
//        return ResponseEntity.ok(productDetailService.update(id,b));
//    }
}
