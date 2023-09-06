package datn.goodboy.service;

/**
 * VoucherSevice
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Voucher;
import datn.goodboy.repository.VoucherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {

  // Declare the repository as final to ensure its immutability
  private final VoucherRepository voucherRepository;

  // Use constructor-based dependency injection
  @Autowired
  public VoucherService(VoucherRepository voucherRepository) {
    this.voucherRepository = voucherRepository;
  }

  public List<Voucher> getAllVouchers() {
    return voucherRepository.findAll();
  }

  public Optional<Voucher> getVoucherById(int id) {
    return voucherRepository.findById(id);
  }

  public Voucher saveVoucher(Voucher voucher) {
    return voucherRepository.save(voucher);
  }

  public void deleteVoucher(int id) {
    voucherRepository.deleteById(id);
  }
}
