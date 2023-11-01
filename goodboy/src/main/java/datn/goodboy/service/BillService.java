package datn.goodboy.service;

import datn.goodboy.model.entity.Bill;
<<<<<<< HEAD
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.Pay;
import datn.goodboy.model.request.BillRequest;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.CustomerRepository;
import datn.goodboy.repository.EmployeeRepository;
import datn.goodboy.repository.PayRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
=======
import datn.goodboy.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
>>>>>>> 016da72d15c0a7b87ac48e4830853cc361787361
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 016da72d15c0a7b87ac48e4830853cc361787361
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
<<<<<<< HEAD
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PayRepository payRepository;
=======
>>>>>>> 016da72d15c0a7b87ac48e4830853cc361787361

    @Autowired
    public BillService(BillRepository billRepository) {

        this.billRepository = billRepository;
    }


    public Page<Bill> getPage(Pageable pageable){
<<<<<<< HEAD
        return billRepository.findByDeletedFalse(pageable);
    }

    public List<Bill> getAllBill(){
        return (List<Bill>) billRepository.findAll();
    }

    public Bill getBillById(Integer id) throws NotFoundException {
        return billRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }
=======
        return billRepository.findAll(pageable);
    }

    public ArrayList<Bill> getAllBill(){
        return (ArrayList<Bill>) billRepository.findAll();
    }

>>>>>>> 016da72d15c0a7b87ac48e4830853cc361787361

    public Bill saveBill(Bill bill) {

        return billRepository.save(bill);
    }

<<<<<<< HEAD
=======
    public void deleteBill(int id) {

        billRepository.deleteById(id);
    }

>>>>>>> 016da72d15c0a7b87ac48e4830853cc361787361
    public Optional<Bill> findByIdBill(int id) {

        return billRepository.findById(id);
    }

<<<<<<< HEAD
    public void createBill(BillRequest billRequest) throws NotFoundException {
        if(billRequest!=null){
            Customer customer = customerRepository.findById(billRequest.getId_customer())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy khách hàng")) ;
            Employee employee = employeeRepository.findById(billRequest.getId_employee())
                    .orElseThrow(()-> new NotFoundException("Không tìm thấy nhân viên"));
            Pay pay = payRepository.findById(billRequest.getId_pay())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy Pay"));
            Bill bill = new Bill();
            bill.setCustomer(customer);
            bill.setEmployee(employee);
            bill.setPay(pay);
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
    public Page<Bill> searchBillByCode(Integer numberSize, String code){
        Pageable pageable = PageRequest.of(numberSize,5);
        return billRepository.searchBillByCodeAndDeletedFalse(pageable,code);
    }
    public Page<Bill> filerByMonth(Integer month,Integer numberSize){
        System.out.println(month);
        Pageable pageable = PageRequest.of(numberSize,5);
        return billRepository.findAllByMonthSortedByLatest(month,pageable);
    }
    public void updateBill(BillRequest bill) throws Exception {
        Bill billSys = billRepository.findByCode(bill.getCode())
                .orElseThrow(() -> new Exception("Not found"));
        billSys.setAddress(bill.getAddress());
        System.out.println(bill.getAddress());
        billSys.setPhone(bill.getPhone());
        billRepository.save(billSys);
    }

    public void updateStatus(Integer id, Integer status) throws NotFoundException {
        Bill bill = getBillById(id);
        bill.setStatus(status);
        billRepository.save(bill);
    }
=======
>>>>>>> 016da72d15c0a7b87ac48e4830853cc361787361
}
