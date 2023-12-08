package datn.goodboy.service;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    BigDecimal totalIncom = BigDecimal.valueOf(0);
    try {
      totalIncom = BigDecimal.valueOf(thongKeRepository.totalIncome(date_from, date_to));
      return totalIncom;
    } catch (Exception e) {
      return BigDecimal.valueOf(0);
    }
  }

  public int getTotalBill(LocalDateTime date_from, LocalDateTime date_to) {
    int totalBill = 0;
    try {
      totalBill = thongKeRepository.totalBill(date_from, date_to);
      return totalBill;
    } catch (Exception e) {
      return 0;
    }
  }

  public int getTotalProductSale(LocalDateTime date_from, LocalDateTime date_to) {
    try {
      return thongKeRepository.totalProductSale(date_from, date_to);
    } catch (Exception e) {
      return 0;
    }
  }

  public List<TopProductSales> getTopProductSales(LocalDateTime date_from, LocalDateTime date_to) {
    List<TopProductSales> lisTopProductSales = new ArrayList<TopProductSales>();
    for (Object object : thongKeRepository.getTopProductsSale(date_from, date_to)) {
      Object[] objectArray = (Object[]) object;
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
    Sort sort = Sort.by("createdAt").descending();
    PageRequest pageRequest = PageRequest.of(totalPage, pageSize, sort);
    Page<Bill> billPage = thongKeRepository.findAll(pageRequest);
    return billPage.getContent();
  }

  public int getTodayToProducSales() {
    return this.getTotalProductSale(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59));
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

  public Map<String, BigDecimal> getDoanhThuThang(int year) {
    Map<String, BigDecimal> monthData = new LinkedHashMap<>();
    LocalDate now = LocalDate.now();
    if (now.getYear() != year) {
      for (int month = 1; month <= 12; month++) {
        String label = "Tháng " + month;
        LocalDateTime dateStart = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime dateEnd = dateStart.plusMonths(1).minusDays(1);
        BigDecimal value = getToTalDoanhThu(dateStart, dateEnd);
        monthData.put(label, value);
      }
    } else {
      for (int month = 1; month <= now.getMonthValue(); month++) {
        String label = "Tháng " + month;
        LocalDateTime dateStart = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime dateEnd = dateStart.plusMonths(1).minusDays(1);
        BigDecimal value = getToTalDoanhThu(dateStart, dateEnd);
        monthData.put(label, value);
      }
    }
    return monthData;
  }

  public Map<String, BigDecimal> getDoanhThuNam() {
    Map<String, BigDecimal> yearData = new LinkedHashMap<>();
    LocalDateTime now = LocalDateTime.now();
    for (int i = 4; i >= 0; i--) {
      int year = now.getYear() - i;
      LocalDateTime yearStart = LocalDateTime.of(year, 1, 1, 0, 0);
      LocalDateTime yearEnd = yearStart.plusYears(1).minusDays(1);
      BigDecimal value = getToTalDoanhThu(yearStart, yearEnd);
      yearData.put("Năm " + year, value);
    }
    return yearData;
  }

  public Map<String, BigDecimal> getDoanhThuLastWeek() {
    Map<String, BigDecimal> weekData = new LinkedHashMap<>();

    for (int day = 6; day >= 0; day--) {
      LocalDateTime dayStart = LocalDateTime.now().toLocalDate().minusDays(day).atStartOfDay();
      LocalDateTime dayEnd = dayStart.plusDays(1).minusSeconds(1);

      BigDecimal value = getToTalDoanhThu(dayStart, dayEnd);

      // Format the date as "T [DayOfWeekAbbreviation]"
      String formattedDate = getDayOfWeekAbbreviation(dayStart) + " - " + dayStart.getDayOfMonth() + "/"
          + dayStart.getMonthValue();
      weekData.put(formattedDate, value);
    }
    return weekData;
  }

  public Map<String, BigDecimal> getDoanhThuThangNay() {
    Map<String, BigDecimal> dailyData = new LinkedHashMap<>();
    LocalDate now = LocalDate.now();
    int year = now.getYear();
    int month = now.getMonthValue();

    LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
    LocalDateTime nowDateTime = LocalDateTime.now();

    for (LocalDateTime date = startOfMonth; date.isBefore(nowDateTime.plusDays(1)); date = date.plusDays(1)) {
      String label = getDayOfWeekAbbreviation(date) + " - " + date.getDayOfMonth() +"/"+ date.getMonthValue();
      BigDecimal value = getToTalDoanhThu(date, date.plusDays(1).minusSeconds(1));
      dailyData.put(label, value);
    }
    return dailyData;
  }

  private String getDayOfWeekAbbreviation(LocalDateTime date) {
    int dayOfWeek = date.getDayOfWeek().getValue();
    DateFormatSymbols symbols = new DateFormatSymbols(Locale.forLanguageTag("vi-VN"));
    String[] dayNames = symbols.getShortWeekdays();
    return dayNames[dayOfWeek];
  }
}
