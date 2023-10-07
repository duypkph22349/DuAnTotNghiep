package datn.goodboy.controller.RestController;

import datn.goodboy.model.entity.DiaChi.District;
import datn.goodboy.model.entity.DiaChi.Province;
import datn.goodboy.model.entity.DiaChi.Ward;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@FeignClient(name = "openApi", url = "https://provinces.open-api.vn/api")
public interface OpenApiClient {
    @GetMapping("/p")
    List<Province> getAllProvinces();

    @GetMapping("/d")
    List<District> getAllDistricts();

    @GetMapping("/w")
    List<Ward> getAllWards();
}
