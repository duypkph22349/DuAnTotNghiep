package datn.goodboy.controller.testcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

  @GetMapping("/doanhthu")
  public ResponseEntity<Integer> getDoanhThu(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_to) {
    int totalDoanhThu = thongKeService.getToTalDoanhThu(date_from.atStartOfDay(), date_to.atTime(23, 59, 59));
    return ResponseEntity.ok(totalDoanhThu);
  }

  @GetMapping("/totalbill")
  public int getTotalBill(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_to) {
    return thongKeService.getTotalBill(date_from.atStartOfDay(), date_to.atTime(23, 59, 59));
  }

  @GetMapping("/totalproductsale")
  public int getTotalProductSale(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_to) {
    return thongKeService.getTotalProductSale(date_from.atStartOfDay(), date_to.atTime(23, 59, 59));
  }

  @GetMapping("/topproductsales")
  public List<TopProductSales> getTopProductSales(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_to) {
    return thongKeService.getTopProductSales(date_from.atStartOfDay(), date_to.atTime(23, 59, 59));
  }

  @GetMapping("/recentbills")
  public List<Bill> getRecentBills(@RequestParam int totalPage, @RequestParam int pageSize) {
    return thongKeService.getRecentBill(totalPage, pageSize);
  }
}
