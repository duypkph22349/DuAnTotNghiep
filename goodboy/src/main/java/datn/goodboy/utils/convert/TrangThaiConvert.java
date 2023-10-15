package datn.goodboy.utils.convert;

import org.springframework.stereotype.Component;

@Component
public class TrangThaiConvert {
  public static String convertTrangThai(int status) {
    if (status == 0) {
      return "Kích hoạt";
    }
    if (status == 1) {
      return "Ngừng kích hoạt";
    }
    if(status == 2){

    }
    return "Error";
  }
}
