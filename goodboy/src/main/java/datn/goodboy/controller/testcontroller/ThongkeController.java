package datn.goodboy.controller.testcontroller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.response.TopProductSales;
import datn.goodboy.service.ThongKeService;

@RestController
@RequestMapping("/api/thongke")
public class ThongkeController {

  @Autowired
  private ThongKeService thongKeService;

  // today
  @GetMapping("/todaydoanhthu")
  public ResponseEntity<BigDecimal> getToDayDoanhThu() {
    BigDecimal totalDoanhThu = thongKeService.getTodayToTalDoanhThu();
    return ResponseEntity.ok(totalDoanhThu);
  }

  @GetMapping("/todaytotalbill")
  public ResponseEntity<Integer> getToDayTotalBill() {
    return ResponseEntity.ok(thongKeService.getTodayTotalBill());
  }

  @GetMapping("/todaytotalproductsale")
  public ResponseEntity<Integer> getTotalToDayProductSale() {
    return ResponseEntity.ok(thongKeService.getTodayToProducSales());
  }

  @GetMapping("/todaytopproductsales")
  public ResponseEntity<List<TopProductSales>> getToDayTopProductSales() {
    return ResponseEntity.ok(thongKeService.getToDayTopProductSales());
  }
  // mouth

  @GetMapping("/thismouthdoanhthu")
  public ResponseEntity<BigDecimal> getThisMouthDoanhThu() {
    BigDecimal totalDoanhThu = thongKeService.getThisMouthToTalDoanhThu();
    return ResponseEntity.ok(totalDoanhThu);
  }

  @GetMapping("/thismouthtotalbill")
  public ResponseEntity<Integer> getThisMouthTotalBill() {
    return ResponseEntity.ok(thongKeService.getThisMouthTotalBill());
  }

  @GetMapping("/thismouthtotalproductsale")
  public ResponseEntity<Integer> getThisMouthTotalProductSale() {
    return ResponseEntity.ok(thongKeService.getThisMouthToProducSales());
  }

  @GetMapping("/thismouthtopproductsales")
  public ResponseEntity<List<TopProductSales>> getThisMouthTopProductSales() {
    return ResponseEntity.ok(thongKeService.getThisMouthTopProductSales());
  }
  // year

  @GetMapping("/thisyeardoanhthu")
  public ResponseEntity<BigDecimal> getThisYearDoanhThu() {
    BigDecimal totalDoanhThu = thongKeService.getThisYearToTalDoanhThu();
    return ResponseEntity.ok(totalDoanhThu);
  }

  @GetMapping("/thisyeartotalbill")
  public ResponseEntity<Integer> getThisYearTotalBill() {
    return ResponseEntity.ok(thongKeService.getThisYearTotalBill());
  }

  @GetMapping("/thisyeartotalproductsale")
  public ResponseEntity<Integer> getThisYearTotalProductSale() {
    return ResponseEntity.ok(thongKeService.getThisYearToProducSales());
  }

  @GetMapping("/thisyeartopproductsales")
  public ResponseEntity<List<TopProductSales>> getThisYearTopProductSales() {
    return ResponseEntity.ok(thongKeService.getThisYearTopProductSales());
  }

  @GetMapping("/thisyearrecentbills")
  public ResponseEntity<List<Bill>> getRecentBills(@RequestParam int totalPage, @RequestParam int pageSize) {
    return ResponseEntity.ok(thongKeService.getRecentBill(totalPage, pageSize));
  }
}
