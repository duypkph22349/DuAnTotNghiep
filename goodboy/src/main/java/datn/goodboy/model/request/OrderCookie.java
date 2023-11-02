package datn.goodboy.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCookie {
  String cookieName;
  int orderid;
  int nhanvienid;
  int totalMany;
  List<ProductOrderCookie> productCookie;
}
