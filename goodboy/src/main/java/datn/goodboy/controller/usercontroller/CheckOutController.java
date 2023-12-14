package datn.goodboy.controller.usercontroller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import datn.goodboy.config.ConfigPay;
import datn.goodboy.model.DTO.PaymentResDTO;
import datn.goodboy.model.entity.Bill;
import datn.goodboy.service.BillService;
import datn.goodboy.service.ProductDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CheckOutController {

    @Autowired
    private CartDetailService cartDetailService;
    private static int pay_id;
    @Autowired
    private CartService cartService;
    @Autowired
    private BillService billService;

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/shop/checkout")
    public String viewCheckout(Model model) {
        Cart cart = cartService.getCart();
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId(cart.getId());
        List<Integer> integers = cartDetails.stream() .map(CartDetail::getId)
                .collect(Collectors.toList());
        Bill bill = cartService.getCheckOutPage(integers);
        model.addAttribute("bills",bill);
        model.addAttribute("cartDetails", cartDetails);
        System.out.println(bill.getBillDetail().size());
        return "user/checkout";
    }



    @PostMapping("/checkout")
    public String saveCheckOut(@ModelAttribute("bills") Bill bill, HttpServletRequest request) {
        String paymentOption = request.getParameter("paymentOption");

        if ("0".equals(paymentOption)) {

            return "redirect:" + ConfigPay.vnp_PayUrl;
        } else if ("1".equals(paymentOption)) {
            billService.saveBillAndDetails(bill);
//            productDetailService.updateProductQuantities(bill.getBillDetail());
        }
        return "redirect:/home";
    }

    @GetMapping("/create_payment/{amount}/{id}")
    public ResponseEntity<?> getPay(@PathVariable int amount, @PathVariable int id)
            throws UnsupportedEncodingException {

        pay_id = id;
        // long totalPrice = Long.parseLong(String.valueOf(amount)) * 100;

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";

        String bankCode = "NCB";

        String vnp_TxnRef = ConfigPay.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = ConfigPay.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_ReturnUrl", ConfigPay.vnp_ReturnUrl);
        vnp_Params.put("vnp_Locale", "vn");
        // vnp_Params.put("vnp_ReturnURL",ConfigPay.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigPay.hmacSHA512(ConfigPay.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = ConfigPay.vnp_PayUrl + "?" + queryUrl;
        PaymentResDTO paymentResDTO = new PaymentResDTO();
        paymentResDTO.setUrl(paymentUrl);
        System.out.println(paymentResDTO);

        return ResponseEntity.ok(paymentResDTO);
    }
}
