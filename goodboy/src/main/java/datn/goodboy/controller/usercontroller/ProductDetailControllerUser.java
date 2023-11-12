package datn.goodboy.controller.usercontroller;

import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProductDetailControllerUser {

    @Autowired
    ProductDetailRepository repo;

    @RequestMapping("/products")
    public String getProductPage(@RequestParam Optional<Boolean> price, @RequestParam Optional<Integer> categoryId, @RequestParam Optional<Integer> page,
                                 Model model) {
        Sort sort = Sort.by("price").ascending();
        if (price.isPresent() && !price.get()) {
            sort = Sort.by("price").descending();
        }

        int p = 0;
        if(page.isPresent()) {
            p = page.get();
        }
        Pageable pageable = null;
        pageable = PageRequest.of(p, 8, sort);

        if (categoryId.isPresent()) {
            System.err.println(2);
//            model.addAttribute("products", repo.findByCategoryId(categoryId.get(), pageable));
        } else {
            model.addAttribute("products", repo.findAll(pageable));
        }

//        model.addAttribute("categories", cateRepo.findAll());

        return "user/product";
    }

    @GetMapping("/products/detail/{slug}")
    public String getProductDetail(@PathVariable Optional<String> slug, Model model) {
        if (slug.isPresent()) {
//            ProductDetail product = repo.findBySlug(slug.get());
//            Color color = product.getIdColor().get(0);
//            model.addAttribute("product", product);
//            model.addAttribute("color", color);
            model.addAttribute("products", repo.findAll());
            return "product_detail";
        } else
            return "redirect:/products";
    }

}
