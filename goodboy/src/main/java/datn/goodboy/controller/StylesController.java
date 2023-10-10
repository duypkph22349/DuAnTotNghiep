package datn.goodboy.controller;

import datn.goodboy.model.entity.Styles;
import datn.goodboy.service.StylesService;
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
@RequestMapping("/api/khan-choang/style")
public class StylesController {
    @Autowired
    private StylesService stylesService;
    private int currentProductCode = 1;
    @GetMapping("/hien-thi")
    public ResponseEntity<Page<Styles>> hienThi(@RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(stylesService.findAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add( @RequestBody Styles b, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        String newProductCode = "SL" + String.format("%d", currentProductCode);
        b.setCode(newProductCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        return ResponseEntity.ok(stylesService.add(b));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update( @RequestBody Styles b, @PathVariable Integer id, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        b.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(stylesService.update(id,b));
    }
}
