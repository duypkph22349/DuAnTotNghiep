
```markdown

## Formatting giá tiền

Biểu thức này sử dụng các chức năng định dạng số và thao tác chuỗi của Thymeleaf để hiển thị phiếu giảm giá bằng Đồng Việt Nam (VND), loại bỏ mọi dấu '.00'.

```html
th:text="${#strings.replace(#numbers.formatDecimal(voucher?.discount, 0, 'COMMA', 2, 'POINT'), '.00', '')} + ' VNĐ'"
```

## Formatting ngày và giờ

Biểu thức này sử dụng định dạng thời gian của Thymeleaf để hiển thị thời gian bắt đầu của phiếu thưởng ở định dạng 'dd-MM-yyyy HH:mm'.

```html
td th:text="${#temporals.format(voucher?.start_time, 'dd-MM-yyyy HH:mm')}"
```

## Order Types

Mã này đại diện cho phương pháp đặt hàng.

- 0: Tại quầy (At the counter)
- 1: Online

## Status cho tất cả bảng

Mã này áp dụng vào status cho tất cả bảng

- 0: Ngừng kích hoạt (Deactivated)
- 1: Kích hoạt (Activated)

## Bill Status Timeline

Mã này áp dụng cho timeline trạng thái đơn hàng

- 1:Chờ xác nhận (Confirmed successfully)
- 2:Chờ lấy hàng
- 3: Chờ giao hàng (Pending delivery)
- 4: Đang giao hàng (In transit)
- 5: Đã giao hàng (Delivered)
- 6: Thành công (Successful)
