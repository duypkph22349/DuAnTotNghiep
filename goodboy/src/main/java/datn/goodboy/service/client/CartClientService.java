package datn.goodboy.service.client;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.repository.CartRepository;
import datn.goodboy.security.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartClientService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AccountInfoService userService;

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
        }
        return quantity;
    }

    public Object findVoucherByCode(String code) {
        return cartRepository.findVoucherByCode(code);
    }
}
