package datn.goodboy.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import datn.goodboy.exeption.rest.ErrorCreateBill;
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

  @JsonProperty("customerID")
  private UUID customerID;
  // o is tai quay 1 is online
  @JsonProperty("orderTypes")
  private int orderTypes;

  @JsonProperty("phoneNumber")
  private String phoneNumber;

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

  @JsonProperty("transferMoney")
  private float transferMoney; // Corrected the field name

  @JsonProperty("reductionAmount")
  private float reductionAmount; // Added the missing field

  @JsonProperty("cashReturn")
  private float cashReturn;

  @JsonProperty("totalShip")
  private float totalShip;

  @JsonProperty("changeAmount")
  private float changeAmount;

  @JsonProperty("voucherid")
  private int voucher;

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
      @JsonProperty("customerID") UUID customerID,
      @JsonProperty("orderTypes") int orderTypes,
      @JsonProperty("phoneNumber") String phoneNumber,
      @JsonProperty("city") String city,
      @JsonProperty("district") String district,
      @JsonProperty("ward") String ward,
      @JsonProperty("fullAddress") String fullAddress,
      @JsonProperty("specificAddress") String specificAddress,
      @JsonProperty("note") String note,
      @JsonProperty("transferMoney") float transferMoney,
      @JsonProperty("cashMoney") float cashMoney,
      @JsonProperty("cashReturn") float cashReturn,
      @JsonProperty("changeAmount") float changeAmount,
      @JsonProperty("totalShip") float totalShip,
      @JsonProperty("totalMoney") float totalMoney,
      @JsonProperty("voucherid") int voucher,
      @JsonProperty("reductionAmount") float reductionAmount) { // Added the missing field
    this.products = products;
    this.customerName = customerName;
    this.employeeID = employeeID;
    this.customerID = customerID;
    this.orderTypes = orderTypes;
    this.phoneNumber = phoneNumber;
    this.city = city;
    this.district = district;
    this.ward = ward;
    this.fullAddress = fullAddress;
    this.specificAddress = specificAddress;
    this.note = note;
    this.transferMoney = transferMoney;
    this.cashMoney = cashMoney;
    this.cashReturn = cashReturn;
    this.changeAmount = changeAmount;
    this.totalShip = totalShip;
    this.totalMoney = totalMoney;
    this.voucher = voucher;
    this.reductionAmount = reductionAmount; // Added the missing field
  }

  public String getErrorMessage() {
    StringBuilder errorMessage = new StringBuilder();

    if (products.isEmpty()) {
      errorMessage.append("Chưa có sản phẩm nào\n");
    } else {
      if (employeeID == null) {
        errorMessage.append("Employee cần được chọn !!!\n");
      }
      if (orderTypes == 0) {
        // check if chọn loại hóa đơn tại quầy
        if (cashMoney + transferMoney < totalMoney) {
          // check if chọn phương thức thanh toán là tiền mặt
          errorMessage.append("Tiền chưa đủ !!!\n");
        }
      }
      if (orderTypes == 1) {
        if (phoneNumber.trim().isEmpty()) {
          errorMessage.append("Số điện thoại không được thiếu !!!\n");
        }
        if (customerName.trim().isEmpty()) {
          errorMessage.append("Tên khách hàng không được thiếu !!!\n");
        }

        if (district.trim().isEmpty()) {
          errorMessage.append("district không được thiếu !!!\n");
        }
        if (ward.trim().isEmpty()) {
          errorMessage.append("ward không được thiếu !!!\n");
        }
        if (fullAddress.trim().isEmpty()) {
          errorMessage.append("fullAddress không được thiếu !!!\n");
        }
        if (specificAddress.trim().isEmpty()) {
          errorMessage.append("specificAddress không được thiếu !!!\n");
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
