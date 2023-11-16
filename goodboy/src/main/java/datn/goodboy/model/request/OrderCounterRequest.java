package datn.goodboy.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import datn.goodboy.exception.ErrorCreateBill;
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

  @JsonProperty("totalMoney")
  private float totalMoney;

  @JsonProperty("cashMoney")
  private float cashMoney;

  @JsonProperty("trasferMoney")
  private float transferMoney;

  @JsonProperty("reductionAmount")
  private float reductionAmount;

  @JsonProperty("cashReturn")
  private float cashReturn;

  @JsonProperty("totalShip")
  private float totalShip;

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

  public String getErrorMessage() {
    StringBuilder errorMessage = new StringBuilder();

    if (products.isEmpty()) {
      errorMessage.append("Chưa có sản phẩm nào<br>");
    } else {
      if (employeeID == null) {
        errorMessage.append("Employee cần được chọn !!!<br>");
      }
      if (orderTypes == 0) {
        // check if chọn loại hóa đơn tại quầy
        if (cashMoney + transferMoney <= totalMoney) {
          // check if chọn phương thức thanh toán là tiền mặt
          errorMessage.append("Tiền chưa đủ !!!<br>");
        }
      }
      if (orderTypes == 1) {
        if (phoneNumber.trim().isEmpty()) {
          errorMessage.append("Số điện thoại không được thiếu !!!<br>");
        }
        if (customerName.trim().isEmpty()) {
          errorMessage.append("Tên khách hàng không được thiếu !!!<br>");
        }

        if (district.trim().isEmpty()) {
          errorMessage.append("district không được thiếu !!!<br>");
        }
        if (ward.trim().isEmpty()) {
          errorMessage.append("ward không được thiếu !!!<br>");
        }
        if (fullAddress.trim().isEmpty()) {
          errorMessage.append("fullAddress không được thiếu !!!<br>");
        }
        if (specificAddress.trim().isEmpty()) {
          errorMessage.append("specificAddress không được thiếu !!!<br>");
        }
      }
    }
    return errorMessage.toString();
  }

  public boolean hasValidationError() {
    String errorMessage = getErrorMessage();
    if (!errorMessage.isEmpty()) {
      throw new ErrorCreateBill(errorMessage);
    }
    return false;
  }
}
