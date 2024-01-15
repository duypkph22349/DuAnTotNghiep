package datn.goodboy.service;

import datn.goodboy.model.entity.Address;
import datn.goodboy.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddressByIdCustomer(UUID id_customer) {
        return addressRepository.findById_customer(id_customer);
    }

    public Address getSave(Address address) {
        return addressRepository.save(address);

    }

    public Optional<Address> findById(UUID id) {
        return addressRepository.findById(id);
    }

    public void deleteById(UUID id) {
        addressRepository.deleteById(id);
    }
}
