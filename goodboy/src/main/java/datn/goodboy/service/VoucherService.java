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

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.entity.VoucherDetail;
import datn.goodboy.model.request.VoucherRequest;
import datn.goodboy.repository.VoucherDetailRepository;
import datn.goodboy.repository.VoucherRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;
import datn.goodboy.service.serviceinterface.PanigationWithSearchStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoucherService implements PanigationInterface<Voucher>, PanigationWithSearchStatus<Voucher> {
  @Autowired
  VoucherRepository voucherRepository;
  @Autowired
  VoucherDetailRepository voucherdetailRepository;

  public List<Voucher> getAllVouchers() {
    return voucherRepository.findAll();
  }

  public Optional<Voucher> getVoucherById(int id) {
    return voucherRepository.findById(id);
  }

  public Voucher getVoucherByIde(int id) {
    Optional<Voucher> voucher = voucherRepository.findById(id);
    if (voucher.isPresent()) {
      return voucher.get();
    }
    return null;
  }

  public Voucher saveVoucher(VoucherRequest voucher) {
    Voucher voucher1 = new Voucher();
    voucher1.setName(voucher.getName());
    voucher1.setQuantily(voucher.getQuantity());
    voucher1.setStart_time(voucher.getStartTime());
    voucher1.setEnd_time(voucher.getEndTime());
    voucher1.setDiscount(voucher.getDiscount());
    voucher1.setStatus(voucher.getStatus());
    voucher1.setTypes(voucher.isTypes());
    voucher1.setMax_discount(voucher.getMaxDiscount());
    voucher1.setMin_order(voucher.getMinOrder());
    voucher1.setDeleted(false);
    return voucherRepository.save(voucher1);
  }

  public void deleteVoucher(int id) {
    Optional<Voucher> voucher = voucherRepository.findById(id);
    if (voucher.isPresent()) {
      if (voucher.get().isDeleted()) {
        voucher.get().setDeleted(false);
      } else {
        voucher.get().setDeleted(true);
      }
      voucherRepository.save(voucher.get());
    }
  }

  // manager
  public Voucher updateVoucher(VoucherRequest request) {
    Optional<Voucher> voucher = voucherRepository.findById(request.getId());
    if (voucher.isPresent()) {
      Voucher voucher1 = voucher.get();
      voucher1.setName(request.getName());
      voucher1.setQuantily(request.getQuantity());
      voucher1.setStart_time(request.getStartTime());
      voucher1.setEnd_time(request.getEndTime());
      voucher1.setDiscount(request.getDiscount());
      voucher1.setStatus(request.getStatus());
      voucher1.setTypes(request.isTypes());
      voucher1.setMax_discount(request.getMaxDiscount());
      voucher1.setMin_order(request.getMinOrder());
      return voucherRepository.save(voucher1);
    } else {
      return null;
    }
  }
  // user

  public List<Voucher> getAllAccountHasVoucher(UUID idVoucher) {
    return null;
  }

  public List<Voucher> getAllVoucherAble() {
    return voucherRepository.getAbleVoucher();
  }

  public void useVoucher(Bill bill, int id) {
    Optional<Voucher> voucher = voucherRepository.findById(id);
    if (voucher.isPresent()) {
      voucher.get().setQuantily(voucher.get().getQuantily() - 1);
      VoucherDetail voucherDetail = new VoucherDetail();
      voucherDetail.setBill(bill);
      voucherDetail.setVoucher(voucher.get());
      voucherRepository.save(voucher.get());
      bill.setReduction_amount(voucherdetailRepository.save(voucherDetail).getMoney_reduction());
      for (BillDetail billDetail : bill.getBillDetail()) {
        billDetail
            .setTotalMoney(billDetail.getTotalMoney() * (1 - (bill.getReduction_amount() / bill.getTotal_money())));
      }
    }
  }

  public void calDiscount() {

  }

  public boolean isVoucherAbleToUse(int totalMoney, int id) {
    LocalDateTime now = LocalDateTime.now();
    Optional<Voucher> voucherOp = voucherRepository.findById(id);
    if (voucherOp.isPresent()) {
      Voucher vc = voucherOp.get();
      if (vc.getQuantily() > 0) {
        if (vc.getStart_time().isBefore(now) && vc.getEnd_time().isAfter(now)) {
          return true;
        }
      }
    }
    return false;
  }

  public VoucherRequest getVoucherRequetById(int id) {
    Optional<VoucherRequest> response = voucherRepository.getResponse(id);
    if (response.isPresent()) {
      return response.get();
    } else {
      return null;
    }
  }

  // panigation start
  // panigation
  @Override
  public List<Voucher> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > getPageNumber(pageSize)) {
      return null;
    }
    List<Voucher> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    // findAll method and pass pageable instance
    Page<Voucher> page = voucherRepository.findAll(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<Voucher> page = voucherRepository.findAll(pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<Voucher> page = voucherRepository.findAll(pageable); // findAll()
    int totalPage = page.getTotalPages();
    return Panigation(pageno, totalPage);
  }

  @Override
  public List<Voucher> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir, String txtSearch,
      int status) {
    if (pageNo > getPageNumber(pageSize)) {
      return null;
    }
    List<Voucher> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    // findAll method and pass pageable instance
    Page<Voucher> page = voucherRepository.searchStatus(pageable, txtSearch, status);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount, String txtSearch, int status) {

    Pageable pageable = PageRequest.of(0, rowcount);
    Page<Voucher> page = voucherRepository.searchStatus(pageable, txtSearch, status);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno, String txtSearch, int status) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<Voucher> page = voucherRepository.searchStatus(pageable, txtSearch, status); // findAll()
    int totalPage = page.getTotalPages();
    return Panigation(pageno, totalPage);
  }

  public int[] Panigation(int pageno, int totalPage) {
    int[] rs;
    if (totalPage <= 1) {
      int[] rs1 = { 1 };
      return rs1;
    } else if (totalPage <= 3) {
      rs = new int[totalPage];
      for (int i = 1; i <= totalPage; i++) {
        rs[i - 1] = i;
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

}
