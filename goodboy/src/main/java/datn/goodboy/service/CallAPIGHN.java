package datn.goodboy.service;

import com.fasterxml.jackson.databind.JsonNode;
import datn.goodboy.model.entity.GiaoHangNhanh;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CallAPIGHN {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create";

    public String getAPIGHN(GiaoHangNhanh giaoHangNhanh) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ShopId", "189834 - 0385090080"); // Điền ShopId của bạn vào đây
        headers.set("Token", "50ea92cd-6b53-11ee-a6e6-e60958111f48");    // Điền Token của bạn vào đây
        HttpEntity<GiaoHangNhanh> entity = new HttpEntity<>(giaoHangNhanh,headers);
        System.out.println(giaoHangNhanh);
        JsonNode resp = restTemplate.postForObject(url, entity, JsonNode.class);
        return resp.get("data").get("total_fee").asText();
    }
}

