package datn.goodboy.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UpdateProductDetail {
  @JsonProperty("id")
  int id;
  @JsonProperty("quantity")
  int quantity;
  @JsonProperty("price")
  float price;
  @JsonProperty("description")
  String description;

  @JsonCreator
  public UpdateProductDetail(
      @JsonProperty("id") int id,
      @JsonProperty("quantity") int quantity,
      @JsonProperty("price") float price,
      @JsonProperty("description") String description) {
    this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.description = description;
  }
}
