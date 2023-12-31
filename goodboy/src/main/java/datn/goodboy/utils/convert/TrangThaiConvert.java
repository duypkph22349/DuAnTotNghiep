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
    if (status == 5) {
      return "<span class=\"badge bg-success\">Thành Công</span>";
    }
    if (status == 1) {
      return "<span class=\"badge text-bg-warning\">Chờ xác nhận</span>";
    }
    if (status == 2) {
      return "<span class=\"badge text-bg-secondary\">Chờ giao hàng</span>";
    }
    if (status == 3) {
      return "<span class=\"badge text-bg-info\">Đang giao hàng</span>";
    }
    if (status == 4) {
      return "<span class=\"badge badge text-bg-light\">Đã giao hàng</span>";
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
}
