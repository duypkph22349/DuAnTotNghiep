package datn.goodboy.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CartCookieService {
  private static final String CART_COOKIE_NAME = "userCart";

  public Map<Long, Integer> getCartItems(HttpServletRequest request) {
    Map<Long, Integer> cartItems = (Map<Long, Integer>) request.getSession().getAttribute("cart");

    if (cartItems == null) {
      cartItems = getCartFromCookie(request);
      if (cartItems != null) {
        request.getSession().setAttribute("cart", cartItems);
      }
    }

    if (cartItems == null) {
      cartItems = new HashMap<>();
    }

    return cartItems;
  }

  public void addToCart(Long productId, int quantity, HttpServletRequest request, HttpServletResponse response) {
    Map<Long, Integer> cartItems = getCartItems(request);

    cartItems.put(productId, cartItems.getOrDefault(productId, 0) + quantity);

    request.getSession().setAttribute("cart", cartItems);
    saveCartToCookie(cartItems, response);
  }

  private Map<Long, Integer> getCartFromCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (CART_COOKIE_NAME.equals(cookie.getName())) {
          String[] items = cookie.getValue().split(",");
          Map<Long, Integer> cartMap = new HashMap<>();
          for (String item : items) {
            String[] parts = item.split(":");
            long productId = Long.parseLong(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            cartMap.put(productId, quantity);
          }
          return cartMap;
        }
      }
    }
    return null;
  }

  private void saveCartToCookie(Map<Long, Integer> cartItems, HttpServletResponse response) {
    List<String> cartValues = new ArrayList<>();
    for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
      cartValues.add(entry.getKey() + ":" + entry.getValue());
    }
    String cartValue = String.join(",", cartValues);

    Cookie cookie = new Cookie(CART_COOKIE_NAME, cartValue);
    cookie.setMaxAge(3600);
    response.addCookie(cookie);
  }
}
