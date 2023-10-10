package datn.goodboy.controller;

import datn.goodboy.model.entity.Material;
import datn.goodboy.model.entity.Product;
import datn.goodboy.service.MaterialService;
import datn.goodboy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/khan-choang/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    private int currentProductCode = 1;
    @GetMapping("/hien-thi")
    public ResponseEntity<Page<Material>> hienThi(@RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(materialService.findAllMaterial(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add( @RequestBody Material b, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        String newProductCode = "MR" + String.format("%d", currentProductCode);
        b.setCode(newProductCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        return ResponseEntity.ok(materialService.add(b));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update( @RequestBody Material b, @PathVariable Integer id, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        b.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(materialService.update(id,b));
    }
}
