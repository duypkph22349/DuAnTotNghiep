package datn.goodboy.utils.convert;

import org.springframework.stereotype.Component;

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
}
