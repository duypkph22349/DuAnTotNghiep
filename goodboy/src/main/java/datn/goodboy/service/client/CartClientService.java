package datn.goodboy.service.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.repository.CartRepository;
import datn.goodboy.security.service.AccountInfoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartClientService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AccountInfoService userService;

    @Autowired
    private HttpServletRequest request;

    private static final String CART_COOKIE_NAME = "userCart";

    public Integer getQuantityByIDCustomer(){
        Integer quantity = 0;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //GET QUANTITY LOGIN

            Account user = userService.getAccountByEmail(authentication.getName());
            quantity = cartRepository.getQuantityByIdCustomer(user.getCustomer().getId());
        }else{
            //GET QUANTITY NO LOGIN
            quantity = 0;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (CART_COOKIE_NAME.equals(cookie.getName())) {
                        if(cookie.getValue() != null){
                            if(deserializeCart(cookie.getValue()) != null){
                                for(Integer i : deserializeCart(cookie.getValue()).values()){
                                    quantity += i;
                                }
                            }
                        }

                    }
                }
            }
        }
        return quantity;
    }

    public Object findVoucherByCode(String code) {
        return cartRepository.findVoucherByCode(code);
    }

    private Map<Integer, Integer> deserializeCart(String cartValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String decodedValue = URLDecoder.decode(cartValue, "UTF-8");
            return objectMapper.readValue(decodedValue, new TypeReference<HashMap<Integer, Integer>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
