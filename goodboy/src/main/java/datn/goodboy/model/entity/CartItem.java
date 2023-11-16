package datn.goodboy.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItem {
    private List<Cart> items = new ArrayList<>();
    private Map<Integer, Integer> danhSachSanPham = new HashMap<>();

    public Map<Integer, Integer> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    public void setDanhSachSanPham(Map<Integer, Integer> danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }


}
