package datn.goodboy.model.entity;

import lombok.*;

import java.util.Arrays;
import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GiaoHangNhanh {
    private Integer payment_type_id = 2;
    private String note = "Tintest 123";
    private String required_note = "KHONGCHOXEMHANG";
    private String return_phone = "0329761626";
    private String return_address = "39 NTT";
    private Integer return_district_id = null;
    private String return_ward_code = "";
    private String client_order_code = "";
    private String from_name = "TinTest124";
    private String from_phone = "0987654321";
    private String from_address = "Hà Nội, Vietnam";
    private String from_ward_name = "Cấn Hữu";
    private String from_district_name = "Quốc Oai";
    private String from_province_name = "Hà Nội";
    private String to_name = "TinTest124";
    private String to_phone = "0987654321";
    private String to_address = "72 Thành Thái, Phường 14, Quận 10, Hồ Chí Minh, Vietnam";
    private String to_ward_name = "Phường 14";
    private String to_district_name = "Quận 10";
    private String to_province_name = "HCM";
    private Integer cod_amount = 2000;
    private String content = "Theo New York Times";
    private Integer weight = 2000;
    private Integer length = 1;
    private Integer width = 19;
    private Integer height = 10;
    private Integer cod_failed_amount = 2000;
    private Integer pick_station_id = 1444;
    private Integer deliver_station_id = null;
    private Integer insurance_value = 100000;
    private Integer service_id = 0;
    private Integer service_type_id = 2;
    private String coupon = null;
    private Long pickup_time = 1692840132L;
    private List<Integer> pick_shift = Arrays.asList(2);
}

