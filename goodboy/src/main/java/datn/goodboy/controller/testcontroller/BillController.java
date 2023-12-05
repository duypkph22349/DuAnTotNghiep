package datn.goodboy.controller.testcontroller;

import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.repository.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController("restbillc")
@RequestMapping("/admin/api/bill")
public class BillController {

  @Autowired
  BillRepository billRepository;

  @GetMapping("{id}")
  public Bill getBill(@PathVariable("id") int id) {
    return billRepository.findById(id).orElse(null);
  }
}
