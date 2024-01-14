package datn.goodboy.service.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import datn.goodboy.exeption.AuthenticationException;
import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.repository.CartDetailRepository;
import datn.goodboy.repository.CartRepository;
import datn.goodboy.repository.ProductDetailRepository;
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
import java.util.Optional;

@Service
public class CartClientService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AccountInfoService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    AccountRepository accountRepository;

    private static final String CART_COOKIE_NAME = "userCart";

    public Integer getQuantityByIDCustomer(){
        Integer quantity = 0;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //GET QUANTITY LOGIN

            Account user = userService.getAccountByEmail(authentication.getName());
            quantity = String.valueOf(cartRepository.getQuantityByIdCustomer(user.getCustomer().getId())) == "" ?0 :cartRepository.getQuantityByIdCustomer(user.getCustomer().getId()) ;
        }else{
            //GET QUANTITY NO LOGIN
            quantity = 0;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (CART_COOKIE_NAME.equals(cookie.getName())) {
                        if(cookie.getValue() != null){
                            for (Integer e : deserializeCart(cookie.getValue()).values()){
                                quantity += e;
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

    public CartDetail findCartDetailById(Integer idProduct){
        Cart cart = getCart();
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(idProduct);

        if (productDetailOptional.isPresent()) {
            ProductDetail productDetail = productDetailOptional.get();
            Optional<CartDetail> existingCartDetailOptional = cart.getCartDetails()
                    .stream()
                    .filter(cd -> cd.getProductDetail().equals(productDetail))
                    .findFirst();

            if (existingCartDetailOptional.isPresent()) {
                CartDetail existingCartDetail = existingCartDetailOptional.get();
               return existingCartDetail;
            }else{
                return null;
            }
        }
        return null;

    }

    public Cart getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Account account = accountRepository.fillAcccoutbyEmail(currentUserName);
            if (account == null) {
                throw new AuthenticationException("Vui lòng đăng nhập");
            }
            Cart cart = null;
            if (account.getCustomer().getCart() == null) {
                cart = new Cart();
                cart.setCustomer(account.getCustomer());
                return cartRepository.save(cart);
            } else {
                return account.getCustomer().getCart();
            }
        } else {
            throw new AuthenticationException("Vui lòng đăng nhập");
        }
    };
}
