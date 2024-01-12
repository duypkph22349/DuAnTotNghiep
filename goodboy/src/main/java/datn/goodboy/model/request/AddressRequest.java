package datn.goodboy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    String id;

    String name;

    String email;

    String phone_number;

    String code_city;

    String code_district;

    String code_ward;

    String city;

    String ward;

    String district;

    String address;

    Boolean is_default;
}
