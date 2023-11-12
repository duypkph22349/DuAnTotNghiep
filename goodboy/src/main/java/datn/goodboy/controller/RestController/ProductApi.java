package datn.goodboy.controller.RestController;

import datn.goodboy.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductApi {

    @Autowired
    ColorRepository repo;

//    @PostMapping("/api/product-color/{colorId}")
//    public ResponseEntity<List<ProductImage>> postColor(@PathVariable Optional<Integer> colorId) {
//        List<ProductImage> images = repo.findById(colorId.orElse(0)).get().getImages();
//        return new ResponseEntity<List<ProductImage>>(images, HttpStatus.OK);
//    }
}
