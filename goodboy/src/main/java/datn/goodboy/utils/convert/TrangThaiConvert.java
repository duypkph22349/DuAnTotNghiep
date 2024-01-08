package datn.goodboy.utils.convert;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.Voucher;

@Component
public class TrangThaiConvert {
  public static String convertTrangThai(int status) {
    if (status == 0) {
      return "Ngừng Hoạt Động";
    }
    if (status == 1) {
      return "Hoạt Động";
    }
    if (status == 2) {
    }
    return "Error";
  }

  public static BigDecimal convertNumber(Double yourNumber) {
    BigDecimal bd = new BigDecimal(yourNumber);
    // String result = bd.toPlainString();
    return bd;
  }

  public static String convertTrangThaiEmployee(int status) {
    if (status == 0) {
      return "Hoạt Động";
    }
    if (status == 1) {
      return "Ngừng Hoạt Động";
    }
    if (status == 2) {
    }
    return "Error";
  }

  public static String statusOfAccount(int status) {
    if (status == 0) {
      return "Ngừng kích hoạt";
    }
    if (status == 1) {
      return "Kích hoạt";
    }
    if (status == -1) {
      return "Đã xóa";
    }
    return "Error";
  }

  public static String statusOfBill(int status) {
    if (status == 6) {
      return "<span class=\"badge text-bg-danger\">Đã Hủy</span>";
    }
    if (status == 5) {
      return "<span class=\"badge text-bg-success\">Thành công</span>";
    }
    if (status == 1) {
      return "<span class=\"badge text-bg-warning\">Chờ xác nhận</span>";
    }
    if (status == 2) {
      return "<span class=\"badge text-bg-secondary\">Chờ lấy hàng</span>";
    }
    if (status == 3) {
      return "<span class=\"badge text-bg-info\">Chờ giao hàng</span>";
    }
    if (status == 4) {
      return "<span class=\"badge badge text-bg-light\">Đang giao hàng</span>";
    }
    if (status == -1) {
      return "<span class=\"badge text-bg-danger\">Đã Hủy</span>";
    }
    if (status == -2) {
      return "<span class=\"badge text-bg-danger\">Đã Hủy</span>";
    }
    return "<span class=\"badge text-bg-dark\">Không xác định</span>";
  }

  public static String statusOfVoucher(Voucher voucher) {
    LocalDateTime now = LocalDateTime.now();
    if (voucher.isDeleted()) {
      return "<span class=\"badge bg-danger\">Đã Xóa</span>";
    }
    if (voucher.getStatus() == 0) {
      return "<span class=\"badge bg-secondary\">Vô hiệu hóa</span>";
    }
    if (voucher.getQuantily() <= 0) {
      return "<span class=\"badge bg-danger\">Hết số lượng</span>";
    }
    if (voucher.getEnd_time().isBefore(now)) {
      return "<span class=\"badge bg-warning\">Hết hạn</span>";
    }
    if (voucher.getStatus() == 1 && (voucher.getStart_time().isBefore(now)
        && voucher.getEnd_time().isAfter(now))) {
      return "<span class=\"badge bg-success\">Đang áp dụng</span>";
    }
    if (voucher.getStatus() == 1 && voucher.getStart_time().isAfter(now)) {
      Duration timeUntilStart = Duration.between(now, voucher.getStart_time());
      if (timeUntilStart.toHours() < 24) {
        if (timeUntilStart.toHours() >= 1) {
          long remainingHours = timeUntilStart.toHours();
          return "<span class=\"badge bg-success\">Chưa được áp dụng - Còn " + remainingHours + " giờ</span>";
        } else {
          long remainingMinutes = timeUntilStart.toMinutes();
          return "<span class=\"badge bg-success\">Chưa được áp dụng - Còn " + remainingMinutes + " phút</span>";
        }
      } else {
        return "<span class=\"badge bg-success\">Chưa được áp dụng</span>";
      }
    }
    return "<span class=\"badge text-bg-dark\">Không xác định</span>";
  }

  public static String ratingAvager(Double rating, int totalRatings) {
    if (totalRatings == 0) {
      return """
          <small class=\"fa fa-star mr-1\"></small>
          <small class=\"fa fa-star mr-1\"></small>
          <small class=\"fa fa-star mr-1\"></small>
          <small class=\"fa fa-star mr-1\"></small>
          <small class=\"fa fa-star mr-1\"></small>
          <small>(0)</small>
          """;
    }
    int roundedRating = (int) Math.round(rating);
    double remainder = rating - roundedRating;

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < 5; i++) {
      if (i < roundedRating) {
        result.append("<small class=\"fa fa-star text-primary mr-1\"></small>");
      } else if (i == roundedRating && remainder > 0) {
        result.append("<small class=\"fa fa-star-half text-primary mr-1\"></small>");
      } else {
        result.append("<small class=\"fa fa-star mr-1\"></small>");
      }
    }
    result.append("<small>(").append(totalRatings).append(")</small>");
    return result.toString();
  }

  public static String rating(Double rating) {
    int roundedRating = (int) Math.round(rating);
    double remainder = rating - roundedRating;

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < 5; i++) {
      if (i < roundedRating) {
        result.append("<small class=\"fa fa-star text-primary mr-1\"></small>");
      } else if (i == roundedRating && remainder > 0) {
        result.append("<small class=\"fa fa-star-half text-primary mr-1\"></small>");
      } else {
        result.append("<small class=\"fa fa-star mr-1\"></small>");
      }
    }
    return result.toString();
  }
}
