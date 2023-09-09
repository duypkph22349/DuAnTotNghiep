package datn.goodboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.VoucherDetail;
import datn.goodboy.repository.VoucherDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherDetailService {

  // Declare the repository as final to ensure its immutability
  private final VoucherDetailRepository voucherdetailRepository;

  // Use constructor-based dependency injection
  @Autowired
  public VoucherDetailService(VoucherDetailRepository voucherdetailRepository) {
    this.voucherdetailRepository = voucherdetailRepository;
  }

  public List<VoucherDetail> getAllVoucherDetails() {
    return voucherdetailRepository.findAll();
  }

  public Optional<VoucherDetail> getVoucherDetailById(int id) {
    return voucherdetailRepository.findById(id);
  }

  public VoucherDetail saveVoucherDetail(VoucherDetail voucherdetail) {
    return voucherdetailRepository.save(voucherdetail);
  }

  public void deleteVoucherDetail(int id) {
    voucherdetailRepository.deleteById(id);
  }
}
