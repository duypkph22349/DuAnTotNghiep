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
  public int getToDayTotalBill() {
    return thongKeService.getTodayTotalBill();
  }

  @GetMapping("/todaytotalproductsale")
  public int getTotalToDayProductSale() {
    return thongKeService.getTodayToProducSales();
  }

  @GetMapping("/todaytopproductsales")
  public List<TopProductSales> getToDayTopProductSales() {
    return thongKeService.getToDayTopProductSales();
  }
  // mouth

  @GetMapping("/thismouthdoanhthu")
  public ResponseEntity<BigDecimal> getThisMouthDoanhThu() {
    BigDecimal totalDoanhThu = thongKeService.getThisMouthToTalDoanhThu();
    return ResponseEntity.ok(totalDoanhThu);
  }

  @GetMapping("/thismouthtotalbill")
  public int getThisMouthTotalBill() {
    return thongKeService.getThisMouthTotalBill();
  }

  @GetMapping("/thismouthtotalproductsale")
  public int getThisMouthTotalProductSale() {
    return thongKeService.getThisMouthToProducSales();
  }

  @GetMapping("/thismouthtopproductsales")
  public List<TopProductSales> getThisMouthTopProductSales() {
    return thongKeService.getThisMouthTopProductSales();
  }
  // year

  @GetMapping("/thisyeardoanhthu")
  public ResponseEntity<BigDecimal> getThisYearDoanhThu() {
    BigDecimal totalDoanhThu = thongKeService.getThisYearToTalDoanhThu();
    return ResponseEntity.ok(totalDoanhThu);
  }

  @GetMapping("/thisyeartotalbill")
  public int getThisYearTotalBill() {
    return thongKeService.getThisYearTotalBill();
  }

  @GetMapping("/thisyeartotalproductsale")
  public int getThisYearTotalProductSale() {
    return thongKeService.getThisYearToProducSales();
  }

  @GetMapping("/thisyeartopproductsales")
  public List<TopProductSales> getThisYearTopProductSales() {
    return thongKeService.getThisYearTopProductSales();
  }

  @GetMapping("/thisyearrecentbills")
  public List<Bill> getRecentBills(@RequestParam int totalPage, @RequestParam int pageSize) {
    return thongKeService.getRecentBill(totalPage, pageSize);
  }
}
