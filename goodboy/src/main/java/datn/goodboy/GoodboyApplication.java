package datn.goodboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GoodboyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodboyApplication.class, args);
	}
}
