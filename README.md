-->format Tiền   
th:text="${#strings.replace(#numbers.formatDecimal(voucher?.discount, 0, 'COMMA', 2, 'POINT'), '.00', '')} + ' VNĐ'">  
--> format date time  
td th:text="${#temporals.format(voucher?.start_time, 'dd-MM-yyyy HH:mm')}">  
  
-->ordertype ở bill  
0- tại quầy  
1- online  
-->status trạng thái cho tất cả bảng  
0-ngừng kích hoạt   
1- kích hoạt  
-->bill trạng thái timeline  
0-chờ xác nhận  
1- xác nhận thành công  
2-chờ giao hàng  
3-đang giao hàng  
4-đã giao hàng  
5-thành công  


