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
}
