package datn.goodboy.controller.rest;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.request.BillRequest;
import datn.goodboy.service.BillService;
import javassist.NotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/bill")
public class AdminBill {

    @Autowired
    BillService billService;

    @GetMapping("all-bill")
    public Page<Bill> getAllBill(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return billService.getPage(pageable);
    }

    @GetMapping("statusPay/{status}")
    public Page<Bill> getBillStatusPay(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                       @PathVariable int status) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return billService.findByStatusPay(pageable, status);
    }

    @GetMapping("orderType/{id}")
    public Page<Bill> getBillOrderType(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                       @PathVariable int id) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return billService.findByOrderType(pageable, id);
    }

    @PostMapping("filterDate")
    public Page<Bill> getFilterDate(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {

        // Chuyển đổi từ Date thành LocalDateTime
        LocalDateTime localDateTimeStart = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDateTimeEnd = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Pageable pageable = PageRequest.of(pageNumber, 10);
        return billService.filterDate(pageable, localDateTimeStart, localDateTimeEnd);
    }

    @GetMapping("bill-filter/{status}")
    public Page<Bill> getBillStatus(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                    @PathVariable int status) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return billService.getPageStatus(pageable, status);
    }

    @PostMapping("create-bill")
    public ResponseEntity<?> createBill(@RequestBody BillRequest billRequest) {
        try {
            billService.createBill(billRequest);
            return ResponseEntity.ok("succ");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBill(@RequestBody BillRequest billRequest) {
        try {
            System.out.println(billRequest.getPhone());
            billService.updateBill(billRequest);
            return ResponseEntity.ok("succ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> setStatusBill(@PathVariable int id) {
        try {
            billService.setStatus2(id);
            return ResponseEntity.ok("succ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping("/search-by-code")
    public ResponseEntity<?> search(@RequestParam(name = "code", defaultValue = "null") String code,
                                    @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
        try {
            return ResponseEntity.ok(billService.searchBillByCode(0, code));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping("/filter-by-month")
    public ResponseEntity<?> filterByMonth(@RequestParam(name = "month", defaultValue = "1") Integer month,
                                           @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
        try {
            return ResponseEntity.ok(billService.filerByMonth(month, pageNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping("/get-file-excel")
    public ResponseEntity<?> getFileExcel() throws IOException {
        try {
            List<Bill> billList = billService.getAllBill();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Thông tin billl");
            String[] headersSheet = { "Mã hóa đơn", "Khách hàng", "Nhân viên", "Phương thức thanh toán",
                    "Ngày hoàn thành",
                    "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Phí vận chuyển", "Tổng tiền",
                    " Trạng thái thanh toán",
                    "Số tiền giảm giá", "Tiền đặt cọc", "Loại đơn hàng", "Ghi chú", "Trạng thái", "Ngày tạo",
                    "Ngày cập nhật" };
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headersSheet.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headersSheet[i]);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            int rowNum = 1;
            for (Bill bill : billList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(bill.getCode());
                row.createCell(1).setCellValue(bill.getCustomer().getName());
                row.createCell(2).setCellValue(bill.getEmployee().getName());
                row.createCell(3).setCellValue(bill.getPay().getPayment_method());
                row.createCell(4).setCellValue(bill.getCompletion_date().format(formatter));
                row.createCell(5).setCellValue(bill.getCustomer_name());
                row.createCell(6).setCellValue(bill.getPhone());
                row.createCell(7).setCellValue(bill.getAddress());
                row.createCell(8).setCellValue(bill.getMoney_ship());
                row.createCell(9).setCellValue(bill.getTotal_money());
                row.createCell(10).setCellValue(bill.getStatus_pay());
                row.createCell(11).setCellValue(bill.getReduction_amount());
                row.createCell(12).setCellValue(bill.getDeposit());
                row.createCell(13).setCellValue(bill.getLoaiDon());
                row.createCell(14).setCellValue(bill.getNote());
                row.createCell(15).setCellValue(bill.getStatus());
                row.createCell(16).setCellValue(bill.getCreatedAt().format(formatter));
                row.createCell(17).setCellValue(bill.getUpdatedAt().format(formatter));
            }

            // Tạo luồng dữ liệu để ghi workbook vào
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            // Tạo InputStreamResource từ luồng dữ liệu
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            InputStreamResource resource = new InputStreamResource(inputStream);

            // Thiết lập thông tin phản hồi HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bills.xlsx");

            // Trả về phản hồi HTTP với file Excel
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType
                            .parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

}
