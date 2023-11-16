package datn.goodboy.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderCounterRequest {
  @JsonProperty("products")
  private List<Product> products;

  @JsonProperty("customerName")
  private String customerName;

  @JsonProperty("employeeID")
  private UUID employeeID;

  @JsonProperty("orderTypes")
  private int orderTypes;
  // 0 là tại quầy 1 là online.
  @JsonProperty("phoneNumber")
  private String phoneNumber;

  @JsonProperty("birthdate")
  private String birthdate;

  @JsonProperty("gender")
  private String gender;

  @JsonProperty("city")
  private String city;

  @JsonProperty("district")
  private String district;

  @JsonProperty("ward")
  private String ward;

  @JsonProperty("fullAddress")
  private String fullAddress;

  @JsonProperty("specificAddress")
  private String specificAddress;

  @JsonProperty("note")
  private String note;
  // o is tien mat 1 la chuyen khoan
  @JsonProperty("payMethod")
  private int payMethod;

  @JsonProperty("totalMoney")
  private int totalMoney;

  @JsonProperty("cashMoney")
  private int cashMoney;

  @JsonProperty("reductionAmount")
  private int reduction_amount;

  @JsonProperty("cashReturn")
  private int cashReturn;

  @JsonProperty("totalShip")
  private int totalShip;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  public static class Product {
    @JsonProperty("id")
    private int id;

    @JsonProperty("quantity")
    private int quantity;
  }

  @JsonCreator
  public OrderCounterRequest(
      @JsonProperty("products") List<Product> products,
      @JsonProperty("customerName") String customerName,
      @JsonProperty("employeeID") UUID employeeID,
      @JsonProperty("orderTypes") int orderTypes,
      @JsonProperty("phoneNumber") String phoneNumber,
      @JsonProperty("birthdate") String birthdate,
      @JsonProperty("gender") String gender,
      @JsonProperty("city") String city,
      @JsonProperty("district") String district,
      @JsonProperty("ward") String ward,
      @JsonProperty("fullAddress") String fullAddress,
      @JsonProperty("specificAddress") String specificAddress,
      @JsonProperty("note") String note) {
    this.products = products;
    this.customerName = customerName;
    this.employeeID = employeeID;
    this.orderTypes = orderTypes;
    this.phoneNumber = phoneNumber;
    this.birthdate = birthdate;
    this.gender = gender;
    this.city = city;
    this.district = district;
    this.ward = ward;
    this.fullAddress = fullAddress;
    this.specificAddress = specificAddress;
    this.note = note;
  }

  public String errorMessage;

  public String VaidError() {
    return errorMessage;
  }

  public boolean hasVaidError() {
    Boolean isHasError = false;
    if (orderTypes == 1) {
      // check if chọn loại hóa đơn tại quầy
      if (payMethod == 1) {
        // check if chọn phương thức thanh toán là tiền mặt

      } else if (payMethod == 0) {
        // check if chọn phương thức thanh toán là tiền chuyển khoản

      } else {
        // check if chọn phương thức thanh toán ko xác định
      }
    }
    if (orderTypes == 0) {
      // check if loai hoa don online
    }

    return isHasError;
  }
}
