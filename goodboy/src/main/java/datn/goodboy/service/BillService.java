package datn.goodboy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import datn.goodboy.model.entity.*;
import datn.goodboy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import datn.goodboy.model.request.BillRequest;
import javassist.NotFoundException;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    @Autowired
    private PayRepository payRepository;

    @Autowired
    private AccountRepository accountRepository;

    public BillService() {
        this.billRepository = billRepository;
    }

    public Page<Bill> getPage(Pageable pageable) {
        return billRepository.findByDeletedFalse(pageable);
    }

    public List<Bill> getAllBill() {
        return (List<Bill>) billRepository.findAll();
    }

    public Bill getBillById(Integer id) throws NotFoundException {
        return billRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }

    public Bill saveBill(Bill bill) {

        return billRepository.save(bill);
    }

    public void deleteBill(int id) {

        billRepository.deleteById(id);
    }

    public Optional<Bill> findByIdBill(int id) {

        return billRepository.findById(id);
    }

    public void createBill(BillRequest billRequest) throws NotFoundException {
        if (billRequest != null) {
            Customer customer = customerRepository.findById(billRequest.getId_customer())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy khách hàng"));
            Employee employee = employeeRepository.findById(billRequest.getId_employee())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy nhân viên"));
            Pay pay = payRepository.findById(billRequest.getId_pay())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy Pay"));
            Bill bill = new Bill();
            bill.setCustomer(customer);
            bill.setEmployee(employee);
            bill.setPay(pay);
            bill.setLoaiDon(billRequest.getLoaiDon());
            bill.setCode(billRequest.getCode());
            bill.setAddress(billRequest.getAddress());
            bill.setPhone(billRequest.getPhone());
            bill.setCompletion_date(billRequest.getCompletion_date());
            bill.setConfirmation_date(billRequest.getConfirmation_date());
            bill.setReceived_date(billRequest.getReceived_date());
            bill.setDelivery_date(billRequest.getDelivery_date());
            bill.setDeposit(bill.getDeposit());
            bill.setMoney_ship(billRequest.getMoney_ship());
            bill.setTotal_money(bill.getTotal_money());
            bill.setCustomer_name(customer.getName());
            bill.setStatus(1);

            billRepository.save(bill);
        }
    }

    public Page<Bill> searchBillByCode(Integer numberSize, String code) {
        Pageable pageable = PageRequest.of(numberSize, 5);
        return billRepository.searchBillByCodeAndDeletedFalse(pageable, code);
    }

    public Page<Bill> filerByMonth(Integer month, Integer numberSize) {
        Pageable pageable = PageRequest.of(numberSize, 5);
        return billRepository.findAllByMonthSortedByLatest(month, pageable);
    }

    public void updateBill(BillRequest bill) throws Exception {
        Bill billSys = billRepository.findByCode(bill.getCode())
                .orElseThrow(() -> new Exception("Not found"));
        billSys.setAddress(bill.getAddress());
        billSys.setPhone(bill.getPhone());
        billRepository.save(billSys);
    }

    public void updateStatus(Integer id, Integer status) throws NotFoundException {
        Bill bill = getBillById(id);
        bill.setStatus(status);
        billRepository.save(bill);
    }

    public void updateStatusPay(Integer id, Integer status_pay) throws NotFoundException {
        Bill bill = getBillById(id);
        bill.setStatus_pay(status_pay);
        billRepository.save(bill);
    }

    public UUID getCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Account account = accountRepository.fillAcccoutbyEmail(currentUserName);
            return account.getCustomer().getId();
        }
        return null;
    }


    public List<Bill> findBillsByCustomerId(UUID customerId){
        return billRepository.findByCustomer_Id(customerId);
    }

    public int getBillCountByStatus(int status) {
        return billRepository.countByStatus(status);
    }


}
