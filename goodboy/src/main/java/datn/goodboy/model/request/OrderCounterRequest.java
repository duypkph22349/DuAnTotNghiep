package datn.goodboy.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderCounterRequest {
  @JsonProperty("products")
  private List<Product> products;

  @JsonProperty("customerName")
  private String customerName;

  @JsonProperty("employeeID")
  private String employeeID;

  @JsonProperty("orderTypes")
  private String orderTypes;

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

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Product {
    @JsonProperty("id")
    private String id;

    @JsonProperty("quantity")
    private String quantity;

  }
}
