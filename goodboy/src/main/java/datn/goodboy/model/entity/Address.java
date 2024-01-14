package datn.goodboy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "addresss")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer id_customer;

    @Column(name = "ten_dia_chi")
    private String tenDiaChi;


    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;


    @Column(name = "sdt_nguoi_nhan")
    private String sdt_nguoi_nhan;

    @Column(name = "xa")
    private String xa;

    @Column(name = "huyen")
    private String huyen;

    @Column(name = "thanh_pho")
    private String thanh_pho;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "email")
    private String email;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
