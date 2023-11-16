
th:text="${#strings.replace(#numbers.formatDecimal(voucher?.discount, 0, 'COMMA', 2, 'POINT'), '.00', '')} + ' VNĐ'">
format Tiền 
<td th:text="${#temporals.format(voucher?.start_time, 'dd-MM-yyyy HH:mm')}"></td>
--> format date time

