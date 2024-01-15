package datn.goodboy.model.entity;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  UUID id;

  @Column(name = "code", insertable = false, updatable = false)
  String code;

  @Column(name = "name")
  String name;

  @Column(name = "gender")
  boolean gender;

  @Column(name = "phone")
  String phone;

  @Column(name = "birth_date")
  LocalDate birth_date;

  @Column(name = "thanh_pho")
  String address;

  @Column(name = "new_huyen")
  String city;

  @Column(name = "xa")
  String country;

  @Column(name = "fulladdress")
  String fulladdress;
  @Column(name = "status")
  int status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "update_at")
  private LocalDateTime updatedAt;

  @Column(name = "deleted")
  private boolean deleted;

  @OneToOne(mappedBy = "customer")
  @ToString.Exclude
  @JsonIgnore
  private Cart cart;

  @OneToMany(mappedBy = "customer")
  @ToString.Exclude
  // @JsonProperty("bills")
  @JsonIgnore
  private List<Bill> bills;


  @JsonIgnore
  public List<Bill> getAllBills() {
    if (bills == null) {
      return Collections.emptyList();
    }

    return bills.stream()
        .sorted(Comparator.comparing(Bill::getUpdatedAt).reversed())
        .collect(Collectors.toList());
  }

  @JsonIgnore
  public List<Bill> getConFirmBills() {
    if (bills == null) {
      return Collections.emptyList();
    }

    return bills.stream()
        .filter(bill -> bill.getStatus() == 1)
        .sorted(Comparator.comparing(Bill::getUpdatedAt).reversed())
        .collect(Collectors.toList());
  }

  @JsonIgnore
  public List<Bill> getCancelBills() {
    if (bills == null) {
      return Collections.emptyList();
    }

    return bills.stream()
        .filter(bill -> bill.getStatus() == 6)
        .sorted(Comparator.comparing(Bill::getUpdatedAt).reversed())
        .collect(Collectors.toList());
  }

  @JsonIgnore
  public List<Bill> getWaitingForGetBills() {
    if (bills == null) {
      return Collections.emptyList();
    }

    return bills.stream()
        .filter(bill -> bill.getStatus() == 2)
        .sorted(Comparator.comparing(Bill::getUpdatedAt).reversed())
        .collect(Collectors.toList());
  }

  @JsonIgnore
  public List<Bill> getWaitingForDeliveryBills() {
    if (bills == null) {
      return Collections.emptyList();
    }

    return bills.stream()
        .filter(bill -> bill.getStatus() == 3)
        .sorted(Comparator.comparing(Bill::getUpdatedAt).reversed())
        .collect(Collectors.toList());
  }

  @JsonIgnore
  public List<Bill> getDeliveringBills() {
    if (bills == null) {
      return Collections.emptyList();
    }

    return bills.stream()
        .filter(bill -> bill.getStatus() == 4)
        .sorted(Comparator.comparing(Bill::getUpdatedAt).reversed())
        .collect(Collectors.toList());
  }

  @JsonIgnore
  public List<Bill> getSuccessBills() {
    if (bills == null) {
      return Collections.emptyList();
    }

    return bills.stream()
        .filter(bill -> bill.getStatus() == 5)
        .sorted(Comparator.comparing(Bill::getUpdatedAt).reversed())
        .collect(Collectors.toList());
  }

  @OneToOne(mappedBy = "customer")
  @ToString.Exclude
  @JsonIgnore
  private Account account;
}
