package datn.goodboy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.response.TopProductSales;
import datn.goodboy.repository.ThongKeRepository;

@Service
public class ThongKeService {

  @Autowired
  private ThongKeRepository thongKeRepository;
  @Autowired
  private ProductDetailService productDetailService;

  public BigDecimal getToTalDoanhThu(LocalDateTime date_from, LocalDateTime date_to) {
    try {
      return thongKeRepository.totalIncome(date_from, date_to);
    } catch (Exception e) {
      System.out.println(e);
      return BigDecimal.valueOf(0);
    }
  }

  public int getTotalBill(LocalDateTime date_from, LocalDateTime date_to) {
    try {
      return thongKeRepository.totalBill(date_from, date_to);
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }

  public int getTotalProductSale(LocalDateTime date_from, LocalDateTime date_to) {
    try {
      return thongKeRepository.totalProductSale(date_from, date_to);
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }

  public List<TopProductSales> getTopProductSales(LocalDateTime date_from, LocalDateTime date_to) {
    List<TopProductSales> lisTopProductSales = new ArrayList();
    for (Object object : thongKeRepository.getTopProductsSale(date_from, date_to)) {
      Object[] objectArray = (Object[]) object;
      System.out.println(objectArray);
      TopProductSales topProductSales = new TopProductSales();
      topProductSales.setId_product_detail((Long) objectArray[0]);
      topProductSales.setProductDetail(
          productDetailService.getProductByLong(topProductSales.getId_product_detail()).get());
      topProductSales.setName((String) objectArray[1]);
      topProductSales.setPrice((BigDecimal) objectArray[2]);
      topProductSales.setTotalQuantity((Integer) objectArray[3]);
      topProductSales.setTotalPrice((BigDecimal) objectArray[4]);
      lisTopProductSales.add(topProductSales);
    }
    return lisTopProductSales;
  }

  public List<Bill> getRecentBill(int totalPage, int pageSize) {
    PageRequest pageRequest = PageRequest.of(totalPage, pageSize);
    Page<Bill> billPage = thongKeRepository.findAll(pageRequest);
    return billPage.getContent();
  }

  public int getTodayToProducSales() {
    try {
      return this.getTotalProductSale(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59));
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }

  public BigDecimal getTodayToTalDoanhThu() {
    return this.getToTalDoanhThu(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59));
  }

  public int getTodayTotalBill() {
    return this.getTotalBill(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59));
  }

  public int getThisMouthTotalBill() {
    return this.getTotalBill(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }

  public int getThisMouthToProducSales() {
    return this.getTotalProductSale(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }

  public BigDecimal getThisMouthToTalDoanhThu() {
    return this.getToTalDoanhThu(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }

  public int getThisYearTotalBill() {
    return this.getTotalBill(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }

  public int getThisYearToProducSales() {
    return this.getTotalProductSale(LocalDate.now().withMonth(1).withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }

  public BigDecimal getThisYearToTalDoanhThu() {
    return this.getToTalDoanhThu(LocalDate.now().withMonth(1).withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }

  public List<TopProductSales> getToDayTopProductSales() {
    return this.getTopProductSales(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59));
  }

  public List<TopProductSales> getThisMouthTopProductSales() {
    return this.getTopProductSales(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }

  public List<TopProductSales> getThisYearTopProductSales() {
    return this.getTopProductSales(LocalDate.now().withMonth(1).withDayOfMonth(1).atStartOfDay(),
        LocalDate.now().atTime(23, 59, 59));
  }
}
