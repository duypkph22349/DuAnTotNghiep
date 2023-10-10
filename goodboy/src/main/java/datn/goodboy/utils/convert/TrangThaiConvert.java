package datn.goodboy.utils.convert;

import org.springframework.stereotype.Component;

@Component
public class TrangThaiConvert {
  public static String convertTrangThai(int status) {
    if (status == 0) {
      return "Enable";
    }
    if (status == 1) {
      return "Disable";
    }
    if(status == 2){

    }
    return "Error";
  }
}
