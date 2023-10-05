package datn.goodboy.service;

/**
 * VoucherSevice
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.VoucherRequest;
import datn.goodboy.model.response.AccountResponse;
import datn.goodboy.model.response.VoucherResponse;
import datn.goodboy.repository.VoucherRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoucherService implements PanigationInterface<VoucherResponse> {

  // Declare the repository as final to ensure its immutability
  private final VoucherRepository voucherRepository;

  // Use constructor-based dependency injection
  // admin
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

  // manager
  public Voucher updateVoucher(UUID id_voucher, VoucherRequest request) {
    return null;
  }
  // user

  public List<VoucherResponse> getAllVoucherInAccount(UUID idAccount) {
    return null;
  }

  // panigation start
  @Override
  public List<VoucherResponse> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > getPageNumber(pageSize)) {
      return null;
    }
    List<VoucherResponse> listrs;
    listrs = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    // findAll method and pass pageable instance
    Page<VoucherResponse> page = voucherRepository.getResponsePage(pageable);
    listrs = page.getContent();
    return listrs;
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(1, rowcount);
    Page<VoucherResponse> page = voucherRepository.getResponsePage(pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(1, rowcount);
    Page<VoucherResponse> page = voucherRepository.getResponsePage(pageable);
    int totalPage = page.getTotalPages();
    int[] rs;
    if (totalPage <= 1) {
      int[] rs1 = {};
      return rs1;
    }
    if (totalPage <= 3) {
      rs = new int[totalPage];
      for (int i = 1; i <= totalPage; i++) {
        rs[i] = i;
      }
      return rs;
    } else {
      rs = new int[3];
      if (pageno <= 2) {
        int[] rs1 = { 1, 2, 3 };
        rs = rs1;
      }
      if (pageno > 2) {
        if (pageno < totalPage - 1) {
          int[] rs1 = { pageno - 1, pageno, pageno + 1 };
          rs = rs1;
        }
        if (pageno >= totalPage - 1) {
          int[] rs1 = { totalPage - 2, totalPage - 1, totalPage };
          rs = rs1;
        }
      }
      return rs;
    }
  }
  // panigation end

}
