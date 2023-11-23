package datn.goodboy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.response.TopProductSales;
import datn.goodboy.repository.ThongKeRepository;

@Service
public class ThongKeService {

  @Autowired
  private ThongKeRepository thongKeRepository;

  public int getToTalDoanhThu(LocalDateTime date_from, LocalDateTime date_to) {
    return thongKeRepository.totalIncome(date_from, date_to);
  }

  public int getTotalBill(LocalDateTime date_from, LocalDateTime date_to) {
    return thongKeRepository.totalBill(date_from, date_to);
  }

  public int getTotalProductSale(LocalDateTime date_from, LocalDateTime date_to) {
    return thongKeRepository.totalProductSale(date_from, date_to);
  }

  // public List<TopProductSales> getTopProductSales(LocalDateTime date_from, LocalDateTime date_to) {
  //   return thongKeRepository.getTopProductsSale(date_from, date_to);
  // }

  public List<Bill> getRecentBill(int totalPage, int pageSize) {
    PageRequest pageRequest = PageRequest.of(totalPage, pageSize);
    Page<Bill> billPage = thongKeRepository.findAll(pageRequest);
    return billPage.getContent();
  }
}
