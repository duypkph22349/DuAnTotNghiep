package datn.goodboy.service;


import datn.goodboy.model.entity.CartDetail;
 import datn.goodboy.repository.IShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class ShoppingCartService implements IShoppingCartService {
    Map<Integer, CartDetail> maps = new HashMap<>(); // hiển thị danh sách giỏ hàng

    @Override
    public void add(CartDetail item) {
        CartDetail cartItem = maps.get(item.getId());
        if (cartItem == null) {
            maps.put(item.getId(), item);

        } else {
            cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
        }

    }

    @Override
    public void remove(int id) {
        maps.remove(id);
    }

    @Override
    public CartDetail update(int idProduct, int qty) {
        CartDetail cartItem = maps.get(idProduct);
        cartItem.setQuantity(qty);
        return cartItem;
    }

    @Override
    public void clear() {
        maps.clear();
    }

    @Override
    public Collection<CartDetail> getAllItems() {
        return maps.values();
    }

    @Override
    public int getCount() {
        return maps.values().size();

    }

    @Override
    public double getAmount() {
        return 0;
    }


    public List<CartDetail> getCartItems() {
        return new ArrayList<>(maps.values());
    }
}