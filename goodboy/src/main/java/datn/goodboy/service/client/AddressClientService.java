package datn.goodboy.service.client;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Address;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.request.AddressRequest;
import datn.goodboy.repository.AddressRepository;
import datn.goodboy.repository.CustomerRepository;
import datn.goodboy.security.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AddressClientService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountInfoService userService;

    @Autowired
    private CustomerRepository customerRepository;


    public ArrayList<Address> findAllByIdCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Account user = userService.getAccountByEmail(authentication.getName());
            return (ArrayList<Address>) addressRepository.findAddressByIDCustomer(user.getCustomer().getId());
        }
        return null;
    }

    public Address addNewAddress(AddressRequest req){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = userService.getAccountByEmail(authentication.getName());
        Customer customer = customerRepository.findById(user.getCustomer().getId()).orElse(null);
        ArrayList<Address> list = (ArrayList<Address>) addressRepository.findAddressByIDCustomer(user.getCustomer().getId());

        if(req.getIs_default()){
            for (Address address : list) {
                address.setTrangThai(false);
                addressRepository.save(address);
            }
        }

        Address address = new Address();
        address.setDistrictCode(req.getCode_district());
        address.setProvinceCode(req.getCode_city());
        address.setWardCode(req.getCode_ward());
        address.setTenDiaChi(req.getAddress());
        address.setThanh_pho(req.getCity());
        address.setHuyen(req.getDistrict());
        address.setXa(req.getWard());
        address.setSdt_nguoi_nhan(req.getPhone_number());
        address.setTenNguoiNhan(req.getName());
        address.setId_customer(customer);
        address.setTrangThai(req.getIs_default());
        return addressRepository.save(address);
    }

    public String changeStatus(UUID id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = userService.getAccountByEmail(authentication.getName());
        Customer customer = customerRepository.findById(user.getCustomer().getId()).orElse(null);
        ArrayList<Address> list = (ArrayList<Address>) addressRepository.findAddressByIDCustomer(user.getCustomer().getId());
        Address address = addressRepository.findById(id).get();

        for (Address e : list) {
            e.setTrangThai(false);
            addressRepository.save(address);
        }

        if(address !=  null){
            address.setTrangThai(true);
            addressRepository.save(address);
            return "success";
        }
        return "not found";
    }
}
