package datn.goodboy.controller.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/admin/api/thongke")
public class ThongkeController {

  @Autowired
  private ThongKeService thongKeService;

  // today
  @GetMapping("/todaydoanhthu")
  public ResponseEntity<BigDecimal> getToDayDoanhThu() {
    BigDecimal totalDoanhThu = thongKeService.getTodayToTalDoanhThu();
    return ResponseEntity.ok(totalDoanhThu);
  }

  @GetMapping("/getgrowthanyesterday")
  public float getGrowthanYesterday() {
    return thongKeService.getGrowthThanToday();
  }

  @GetMapping("/todaytotalbill")
  public ResponseEntity<Integer> getToDayTotalBill() {
    return ResponseEntity.ok(thongKeService.getTodayTotalBill());
  }

  @GetMapping("/todaytotalproductsale")
  public ResponseEntity<Integer> getTotalToDayProductSale() {
    return ResponseEntity.ok(thongKeService.getTodayToProducSales());
  }

  @GetMapping("/incometoday")
  public Map<String, BigDecimal> getDoanhNgayHomNay() {
    return thongKeService.getDoanhNgayHomNay();
  }

  @GetMapping("/incomebeforetoday")
  public Map<String, BigDecimal> getDoanhNgayHomQua() {
    return thongKeService.getDoanhNgayHomNay();
  }

  @GetMapping("/incomperhousthisweek")
  public Map<String, BigDecimal> incomperhousthisweek() {
    return thongKeService.getInComePerHoursThisWeek();
  }

  @GetMapping("/incomperhousthisyear")
  public Map<String, BigDecimal> incomperhousthisyear() {
    return thongKeService.getIncomePerHoursThisYear();
  }

  @GetMapping("/incomperhousthismounth")
  public Map<String, BigDecimal> incomperhousthismounth() {
    return thongKeService.getInComePerHoursThisMonth();
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

  @GetMapping("/recentbills")
  public ResponseEntity<List<Bill>> getRecentBills() {
    return ResponseEntity.ok(thongKeService.getRecentBill(0, 10));
  }

  @GetMapping("income/thang")
  public Map<String, BigDecimal> getDoanhThuThang(@RequestParam int year) {
    return thongKeService.getDoanhThuThang(year);
  }

  @GetMapping("income/thisyear")
  public Map<String, BigDecimal> getIncomethisYear() {
    return thongKeService.getDoanhThuThang(LocalDate.now().getYear());
  }

  @GetMapping("income/nam")
  public Map<String, BigDecimal> getDoanhThuNam() {
    return thongKeService.getDoanhThuNam();
  }

  @GetMapping("income/thismouth")
  public Map<String, BigDecimal> getDoanhThangNay() {
    return thongKeService.getDoanhThuThangNay();
  }

  @GetMapping("income/lastweek")
  public Map<String, BigDecimal> getDoanhThuLastWeek() {
    return thongKeService.getDoanhThuLastWeek();
  }
}
