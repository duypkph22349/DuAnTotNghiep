package datn.goodboy.service;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
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

  public BigDecimal getIncomePerHour(int hours, LocalDateTime date_from, LocalDateTime date_to) {
    try {
      BigDecimal totalIncomePerHour = BigDecimal
          .valueOf(thongKeRepository.totalIncomeForHour(hours, date_from, date_to));
      return totalIncomePerHour;
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
      String label = getDayOfWeekAbbreviation(date) + " - " + date.getDayOfMonth() + "/" + date.getMonthValue();
      BigDecimal value = getToTalDoanhThu(date, date.plusDays(1).minusSeconds(1));
      dailyData.put(label, value);
    }
    return dailyData;
  }

  public Map<String, BigDecimal> getDoanhNgayHomNay() {
    Map<String, BigDecimal> hourlyData = new LinkedHashMap<>();
    LocalDateTime nowDateTime = LocalDateTime.now();
    try {
      for (int hour = 0; hour < 24; hour++) {
        LocalDateTime startHour = LocalDateTime.of(nowDateTime.getYear(), nowDateTime.getMonthValue(),
            nowDateTime.getDayOfMonth(), hour, 0);
        LocalDateTime endHour = startHour.plusHours(1);
        String label = startHour.getHour() + ":00";
        BigDecimal value = getToTalDoanhThu(startHour, endHour);
        hourlyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return hourlyData;
  }

  public Map<String, BigDecimal> getDoanhNgayHomQua() {
    Map<String, BigDecimal> hourlyData = new LinkedHashMap<>();
    LocalDateTime nowDateTime = LocalDateTime.now();
    LocalDateTime beforDate = nowDateTime.minusDays(1);
    try {
      for (int hour = 0; hour < 24; hour++) {
        LocalDateTime startHour = LocalDateTime.of(beforDate.getYear(), beforDate.getMonthValue(),
            beforDate.getDayOfMonth(), hour, 0);
        LocalDateTime endHour = startHour.plusHours(1);
        String label = startHour.getHour() + ":00";
        BigDecimal value = getToTalDoanhThu(startHour, endHour);
        hourlyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return hourlyData;
  }

  public Map<String, BigDecimal> getInComePerHoursThisWeek() {
    Map<String, BigDecimal> hourlyData = new LinkedHashMap<>();
    LocalDateTime startOfWeek = LocalDate.now().with(WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek(), 1).atStartOfDay();
    LocalDateTime nowDateTime = LocalDateTime.now();
    try {
      for (int hour = 0; hour < 24; hour++) {
        BigDecimal value = getIncomePerHour(hour, startOfWeek, nowDateTime);
        String label = hour + ":00";
        hourlyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception as needed, log it, or rethrow it.
    }
    return hourlyData;
  }

  public Map<String, BigDecimal> getInComePerHoursThisMonth() {
    Map<String, BigDecimal> hourlyData = new LinkedHashMap<>();
    LocalDate now = LocalDate.now();
    int year = now.getYear();
    int month = now.getMonthValue();

    LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
    LocalDateTime nowDateTime = LocalDateTime.now();

    try {
      for (int hour = 0; hour < 24; hour++) {
        BigDecimal value = getIncomePerHour(hour, startOfMonth, nowDateTime);
        String label = hour + ":00";
        hourlyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception as needed, log it or rethrow it.
    }
    return hourlyData;
  }

  public Map<String, BigDecimal> getIncomePerHoursThisYear() {
    Map<String, BigDecimal> hourlyData = new LinkedHashMap<>();
    LocalDate now = LocalDate.now();
    int year = now.getYear();

    LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0);
    LocalDateTime nowDateTime = LocalDateTime.now();

    try {
      for (int hour = 0; hour < 24; hour++) {
        BigDecimal value = getIncomePerHour(hour, startOfYear, nowDateTime);
        String label = hour + ":00";
        hourlyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception as needed, log it, or rethrow it.
    }
    return hourlyData;
  }

  private String getDayOfWeekAbbreviation(LocalDateTime date) {
    int dayOfWeek = date.getDayOfWeek().getValue();
    DateFormatSymbols symbols = new DateFormatSymbols(Locale.forLanguageTag("vi-VN"));
    String[] dayNames = symbols.getShortWeekdays();
    return dayNames[dayOfWeek];
  }

  public float getGrowthThanToday() {
    LocalDateTime currentDate = LocalDateTime.now();
    LocalDateTime yesterday = currentDate.minus(1, ChronoUnit.DAYS);

    float incomeToday = getToTalDoanhThu(currentDate.toLocalDate().atStartOfDay(), currentDate).floatValue();
    float incomeYesterday = getToTalDoanhThu(yesterday.toLocalDate().atStartOfDay(), yesterday).floatValue();

    if (incomeYesterday != 0) {
      float percentGrow = 100 * (incomeToday / incomeYesterday - 1);
      return percentGrow;
    } else {
      return 0;
    }
  }
}
