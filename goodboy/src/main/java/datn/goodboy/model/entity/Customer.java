package datn.goodboy.model.entity;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
  // @JsonProperty("billsbyStatus")
  private Map<Integer, List<Bill>> getBillByStatus() {
    Map<Integer, List<Bill>> billMap = bills.stream()
        .collect(Collectors.groupingBy(Bill::getStatus));
    return billMap;
  }

  @OneToOne(mappedBy = "customer")
  @ToString.Exclude
  @JsonIgnore
  private Account account;
}
