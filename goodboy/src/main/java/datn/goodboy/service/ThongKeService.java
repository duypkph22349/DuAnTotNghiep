package datn.goodboy.service;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

  public BigDecimal totalIncomeDayInWeeks(int dayofweek, LocalDateTime date_from, LocalDateTime date_to) {
    try {
      BigDecimal totalIncomePerHour = BigDecimal
          .valueOf(thongKeRepository.totalIncomeDayInWeeks(dayofweek, date_from, date_to));
      System.out.println(totalIncomePerHour.toString());
      return totalIncomePerHour;
    } catch (Exception e) {
      System.out.println(e);
      return BigDecimal.valueOf(0);
    }
  }

  private Map<String, BigDecimal> getIncomePerDayOfWeek(LocalDateTime startOfWeek, LocalDateTime endOfWeek) {
    Map<String, BigDecimal> dailyData = new LinkedHashMap<>();
    try {
      for (int dayOfWeek = 1; dayOfWeek <= 7; dayOfWeek++) {
        String label = convertToVietnamese(dayOfWeek);
        BigDecimal value = totalIncomeDayInWeeks(dayOfWeek, startOfWeek, endOfWeek);
        dailyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dailyData;
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
      LocalDateTime dayStart = LocalDateTime.now().minusWeeks(1).toLocalDate().minusDays(day).atStartOfDay();
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

  public Map<String, BigDecimal> getInComePerHoursNgayHomNay() {
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

  public Map<String, BigDecimal> getInComePerHoursNgayHomQua() {
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

  public Map<String, BigDecimal> getIncomePerDayOfThisWeek() {
    LocalDateTime startOfDay = LocalDateTime.now().with(DayOfWeek.of(1)).withHour(0).withMinute(0).withSecond(0)
        .withNano(0);
    return getIncomePerDayOfWeek(startOfDay, LocalDateTime.now());
  }

  public Map<String, BigDecimal> getIncomePerDayOfBeforeWeek() {
    LocalDateTime nowDateTime = LocalDateTime.now();
    LocalDateTime startOfDay = nowDateTime.minusWeeks(1).with(DayOfWeek.of(1)).withHour(0).withMinute(0).withSecond(0)
        .withNano(0);
    LocalDateTime endOfDay = nowDateTime.with(DayOfWeek.of(1)).withHour(0).withMinute(0).withSecond(0)
        .withNano(0).minusDays(1);
    return getIncomePerDayOfWeek(startOfDay, endOfDay);
  }

  public Map<String, BigDecimal> getIncomePerDayOfThisMonth() {
    LocalDate now = LocalDate.now();
    int year = now.getYear();
    int month = now.getMonthValue();
    LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
    LocalDateTime nowDateTime = LocalDateTime.now();
    return getIncomePerDayOfWeek(startOfMonth, nowDateTime);
  }

  public Map<String, BigDecimal> getIncomePerDayOfLastMonth() {
    LocalDate now = LocalDate.now();
    int year = now.getYear();
    int month = now.getMonthValue();
    LocalDateTime startOfMonth = LocalDateTime.of(year, month - 1, 1, 0, 0);
    LocalDateTime endOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
    return getIncomePerDayOfWeek(startOfMonth, endOfMonth);
  }

  private String convertToVietnamese(int dayOfWeek) {
    switch (dayOfWeek) {
      case 2:
        return "Thứ Hai";
      case 3:
        return "Thứ Ba";
      case 4:
        return "Thứ Tư";
      case 5:
        return "Thứ Năm";
      case 6:
        return "Thứ Sáu";
      case 7:
        return "Thứ Bảy";
      case 1:
        return "Chủ Nhật";
      default:
        return "";
    }
  }

  private String getDayOfWeekAbbreviation(LocalDateTime date) {
    int dayOfWeek = date.getDayOfWeek().getValue();
    DateFormatSymbols symbols = new DateFormatSymbols(Locale.forLanguageTag("vi-VN"));
    String[] dayNames = symbols.getShortWeekdays();
    return dayNames[dayOfWeek];
  }

  public float getGrowthThanToday() {
    try {

      LocalDateTime currentDate = LocalDateTime.now();
      LocalDateTime yesterday = currentDate.minus(1, ChronoUnit.DAYS);

      float incomeToday = getToTalDoanhThu(currentDate.toLocalDate().atStartOfDay(), currentDate).floatValue();
      float incomeYesterday = getToTalDoanhThu(yesterday.toLocalDate().atStartOfDay(), yesterday).floatValue();
      if (incomeYesterday == 0) {
        throw new Exception("không có doanh thu");
      }
      if (incomeYesterday != 0) {
        float percentGrow = 100 * (incomeToday / incomeYesterday - 1);
        return percentGrow;
      } else {
        return 0;
      }
    } catch (Exception e) {
      // Handle the exception here
      e.printStackTrace();
      return -1000;
    }
  }

  public float getGrowthThanLastMonth() {
    try {
      LocalDateTime currentDate = LocalDateTime.now();
      LocalDateTime lastMonth = currentDate.minus(1, ChronoUnit.MONTHS);

      float incomeThisMonth = getToTalDoanhThu(currentDate.toLocalDate().atStartOfDay(), currentDate).floatValue();
      float incomeLastMonth = getToTalDoanhThu(lastMonth.toLocalDate().atStartOfDay(),
          lastMonth.withDayOfMonth(lastMonth.toLocalDate().lengthOfMonth()).toLocalDate().atStartOfDay()).floatValue();

      if (incomeLastMonth == 0) {
        throw new Exception("không có doanh thu");
      } else {
        float percentGrow = 100 * (incomeThisMonth / incomeLastMonth - 1);
        return percentGrow;
      }
    } catch (Exception e) {
      // Handle the exception here
      e.printStackTrace();
      return -1000;
    }
  }

  public float getGrowthThanLastYear() {
    try {
      LocalDateTime currentDate = LocalDateTime.now();
      LocalDateTime lastYear = currentDate.minus(1, ChronoUnit.YEARS);

      float incomeThisYear = getToTalDoanhThu(currentDate.withDayOfYear(1).toLocalDate().atStartOfDay(), currentDate)
          .floatValue();
      float incomeLastYear = getToTalDoanhThu(lastYear.withDayOfYear(1).toLocalDate().atStartOfDay(),
          lastYear.withDayOfYear(lastYear.toLocalDate().lengthOfYear()).toLocalDate().atStartOfDay()).floatValue();
      if (incomeLastYear == 0) {
        throw new Exception("không có doanh thu");
      }
      if (incomeLastYear != 0) {
        float percentGrow = 100 * (incomeThisYear / incomeLastYear - 1);
        return percentGrow;
      } else {
        return 0;
      }
    } catch (Exception e) {
      // Handle the exception here
      e.printStackTrace();
      return -1000;
    }
  }

  public Map<String, BigDecimal> getIncomeThisWeek() {
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

  public Map<String, BigDecimal> getIncomeOn(LocalDate now) {
    Map<String, BigDecimal> weeklyData = new LinkedHashMap<>();
    LocalDate startOfWeek = now.with(WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek(), 1);
    LocalDate endOfWeek = startOfWeek.plusDays(6);

    for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
      LocalDateTime startOfDay = date.atStartOfDay();
      LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);

      BigDecimal dailyIncome;
      if (date.isEqual(now)) {
        dailyIncome = getToTalDoanhThu(startOfDay, endOfDay);
      } else {
        dailyIncome = BigDecimal.ZERO;
      }

      // Format the date as "DayOfWeek - dd/MM"
      String formattedDate = getDayOfWeekAbbreviation(startOfDay) + " - " + startOfDay.getDayOfMonth() + "/"
          + startOfDay.getMonthValue();
      weeklyData.put(formattedDate, dailyIncome);
    }
    return weeklyData;
  }

  public Map<String, BigDecimal> getInComePerHoursLastMonth() {
    Map<String, BigDecimal> hourlyData = new LinkedHashMap<>();
    LocalDate now = LocalDate.now();
    LocalDate lastMonth = now.minusMonths(1);
    LocalDateTime startOfLastMonth = lastMonth.withDayOfMonth(1).atStartOfDay();
    LocalDateTime endOfLastMonth = lastMonth.withDayOfMonth(lastMonth.lengthOfMonth()).atTime(23, 59, 59);
    try {
      for (int hour = 0; hour < 24; hour++) {
        BigDecimal value = getIncomePerHour(hour, startOfLastMonth, endOfLastMonth);
        String label = hour + ":00";
        hourlyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception as needed, log it or rethrow it.
    }
    return hourlyData;
  }

  public Map<String, BigDecimal> getInComePerHoursLastWeek() {
    Map<String, BigDecimal> hourlyData = new LinkedHashMap<>();
    LocalDateTime startOfLastWeek = LocalDate.now().minusWeeks(1)
        .with(WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek(), 1).atStartOfDay();
    LocalDateTime endOfLastWeek = startOfLastWeek.plusWeeks(1);

    try {
      for (int hour = 0; hour < 24; hour++) {
        BigDecimal value = getIncomePerHour(hour, startOfLastWeek, endOfLastWeek);
        String label = hour + ":00";
        hourlyData.put(label, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception as needed, log it, or rethrow it.
    }
    return hourlyData;
  }

  public Map<String, BigDecimal> getDoanhThuThangTruoc() {
    Map<String, BigDecimal> dailyData = new LinkedHashMap<>();
    LocalDate now = LocalDate.now();
    int year = now.getYear();
    int month = now.getMonthValue();
    LocalDateTime startOfMonth = LocalDateTime.of(year, month - 1, 1, 0, 0);
    LocalDateTime endOfMonth = LocalDateTime.of(year, month, 1, 0, 0);

    for (LocalDateTime date = startOfMonth; date.isBefore(endOfMonth.plusDays(1)); date = date.plusDays(1)) {
      String label = getDayOfWeekAbbreviation(date) + " - " + date.getDayOfMonth() + "/" + date.getMonthValue();
      BigDecimal value = getToTalDoanhThu(date, date.plusDays(1).minusSeconds(1));
      dailyData.put(label, value);
    }
    return dailyData;
  }
}
