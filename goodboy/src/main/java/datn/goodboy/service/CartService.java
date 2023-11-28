package datn.goodboy.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.repository.CartDetailRepository;
import datn.goodboy.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartDetailRepository cartRepository;

    @Autowired
    private CartRepository cartRp;
    @Autowired
    private AccountRepository accountRepository;

    public Page<CartDetail> getPage(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    public ArrayList<CartDetail> getAllCart() {
        return (ArrayList<CartDetail>) cartRepository.findAll();
    }

    public CartDetail saveCart(CartDetail cart) {

        return cartRepository.save(cart);
    }

    public void addGioHang(Cart cart) {

        cartRp.save(cart);
    }

    public void deleteCart(int id) {

        cartRepository.deleteById(id);
    }

    public Optional<CartDetail> findByIdCart(int id) {

        return cartRepository.findById(id);
    }

    public Cart getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Account account = accountRepository.fillAcccoutbyEmail(currentUserName);
            Cart cart = null;
            if (account.getCustomer().getCart() == null) {
                cart = new Cart();
                cart.setCustomer(account.getCustomer());
                return cartRp.save(cart);
            } else {
                return account.getCustomer().getCart();
            }
        }
        return null;
    }
}
