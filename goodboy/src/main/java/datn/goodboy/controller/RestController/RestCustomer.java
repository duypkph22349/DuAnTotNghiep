package datn.goodboy.controller.RestController;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/api/customer")
public class RestCustomer {

    @GetMapping("/data")
    public String getData() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://provinces.open-api.vn/api/";

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);
        String responseBody = response.getBody();

        return responseBody;
    }
}
