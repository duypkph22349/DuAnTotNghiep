<!-- Định dạng thành Tiền tệ -->
<td th:text="${#strings.replace(
  #numbers.formatDecimal(voucher?.discount, 0, 'COMMA', 2, 'POINT'), 
  '.00', ''
)} + ' VNĐ'"></td>

<!-- Định dạng Thời gian Bắt đầu Ngày và Giờ -->
<td th:text="${#temporals.format(voucher?.start_time, 'dd-MM-yyyy HH:mm')}"></td>
