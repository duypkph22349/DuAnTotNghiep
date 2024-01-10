package datn.goodboy.service.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import datn.goodboy.model.cookieentity.CartResponse;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.ProductDetailRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartCookieService {
  @Autowired
  ProductDetailRepository productRepository;
  private static final String CART_COOKIE_NAME = "userCart";

  public Map<Integer, Integer> getCartItems(HttpServletRequest request, HttpServletResponse response) {
    Map<Integer, Integer> cartItems = getCartFromCookie(request);
    if (cartItems == null) {
      cartItems = new HashMap<>();
    }
    request.getSession().setAttribute(CART_COOKIE_NAME, cartItems);
    System.out.println(cartItems);
    return cartItems;
  }

  private Map<Integer, Integer> getCartFromCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (CART_COOKIE_NAME.equals(cookie.getName())) {
          return deserializeCart(cookie.getValue());
        }
      }
    }
    return null;
  }

  private void saveCartToCookie(Map<Integer, Integer> cartItems, HttpServletRequest request,
      HttpServletResponse response) {
    deleteCookie(request, response, CART_COOKIE_NAME);
    String serializedCart = serializeCart(cartItems);
    if (serializedCart != null) {
      Cookie cookie = new Cookie(CART_COOKIE_NAME, serializedCart);
      cookie.setMaxAge(3600);
      cookie.setPath("/");
      response.addCookie(cookie);
      System.out.println(cookie);
    }
  }

  private String serializeCart(Map<Integer, Integer> cartItems) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return URLEncoder.encode(objectMapper.writeValueAsString(cartItems), "UTF-8");
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
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

  public List<CartResponse> getCartResponses(HttpServletRequest request, HttpServletResponse response) {
    Map<Integer, Integer> cartItems = getCartItems(request, response);
    List<CartResponse> cartResponses = cartItems.entrySet().stream()
        .map(entry -> getProductResponse(entry.getKey(), entry.getValue()))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    System.out.println(cartResponses);
    return cartResponses;
  }

  private CartResponse getProductResponse(Integer productId, Integer quantity) {
    System.out.println(productId);
    try {
      if (productId != null) {
        return productRepository.findById(productId)
            .map(productDetail -> new CartResponse(productDetail, quantity))
            .orElse(null);
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          cookie.setValue("");
          cookie.setMaxAge(0);
          cookie.setPath("/");
          response.addCookie(cookie);
        }
      }
    }
  }

  public void deleltedCartCookie(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(CART_COOKIE_NAME)) {
          cookie.setValue("");
          cookie.setMaxAge(0);
          cookie.setPath("/");
          response.addCookie(cookie);
        }
      }
    }
  }

  public CartResponse addToCart(Integer productId, int quantity, HttpServletRequest request,
      HttpServletResponse response) {
    Map<Integer, Integer> cartItems = getCartItems(request, response);
    ProductDetail productDetail = productRepository.findById(productId).orElse(null);
    if (productDetail != null) {
      if (productDetail.getQuantity() < cartItems.getOrDefault(productId, 0) + quantity) {
        throw new RuntimeException("Sản phẩm chỉ còn: " + productDetail.getQuantity());
      }
      cartItems.put(productId, cartItems.getOrDefault(productId, 0) + quantity);
      // request.getSession().setAttribute(CART_COOKIE_NAME, cartItems);
      saveCartToCookie(cartItems, request, response);
      return new CartResponse(productDetail, quantity);
    } else {
      return null;
    }
  }

  public CartResponse updateToCart(Integer productId, int quantity, HttpServletRequest request,
      HttpServletResponse response) {
    if (quantity == 0) {
      removeFromCart(productId, request, response);
    }
    Map<Integer, Integer> cartItems = getCartItems(request, response);
    ProductDetail productDetail = productRepository.findById(productId).orElse(null);
    if (productDetail != null) {
      if (productDetail.getQuantity() < quantity) {
        throw new RuntimeException("Sản phảm không chỉ còn: " + productDetail.getQuantity());
      }
      cartItems.put(productId, quantity);
      // request.getSession().setAttribute(CART_COOKIE_NAME, cartItems);
      saveCartToCookie(cartItems, request, response);
      return new CartResponse(productDetail, quantity);
    } else {
      return null;
    }
  }

  public void removeFromCart(Integer productId, HttpServletRequest request, HttpServletResponse response) {
    Map<Integer, Integer> cartItems = getCartItems(request, response);
    System.out.println("delete data from cart" + productId);
    cartItems.remove(productId);
    System.out.println(cartItems);
    request.getSession().setAttribute(CART_COOKIE_NAME, cartItems);
    saveCartToCookie(cartItems, request, response);
  }

  public void removeFromCarts(List<String> productIds, HttpServletRequest request, HttpServletResponse response) {
    Map<Integer, Integer> cartItems = getCartItems(request, response);
    for (String productId : productIds){
      cartItems.remove(Integer.parseInt(productId));
    }
    request.getSession().setAttribute(CART_COOKIE_NAME, cartItems);
    saveCartToCookie(cartItems, request, response);
  }

  public Double calculateTotalPrice(List<Integer> cartDetails, HttpServletRequest request,
      HttpServletResponse response) {
    return getCartResponses(request, response).stream()
        .filter(cartResponse -> cartDetails.contains(cartResponse.getProductDetaill().getId()))
        .mapToDouble(cartResponse -> {
          try {
            return cartResponse.getProductDetaill().getPrice() *
                cartResponse.getQuantity();
          } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0; // Default value or handle it based on your requirements
          }
        })
        .sum();
  }

  public CartResponse getCartResponse(HttpServletRequest request, HttpServletResponse response, int productId) {
    Map<Integer, Integer> cartItems = getCartItems(request, response);

    if (cartItems.containsKey(productId)) {
      int quantity = cartItems.get(productId);
      ProductDetail productDetail = productRepository.findById(productId).orElse(null);
      if (productDetail != null) {
        return new CartResponse(productDetail, quantity);
      }
    }
    return null;
  }
}
