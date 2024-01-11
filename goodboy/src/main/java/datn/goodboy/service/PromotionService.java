package datn.goodboy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Promotion;
import datn.goodboy.model.request.VoucherRequest;
import datn.goodboy.repository.PromotionRespository;
import datn.goodboy.service.serviceinterface.PanigationInterface;

/**
 * PromotionService
 */
@Service
public class PromotionService implements PanigationInterface<Promotion> {
  @Autowired
  PromotionRespository respository;

  public List<Promotion> getAllPromotions() {
    return respository.findAll();
  }

  public Optional<Promotion> getPromotionById(UUID id) {
    return respository.findById(id);
  }

  public Promotion getPromotionByIde(UUID id) {
    Optional<Promotion> promotion = respository.findById(id);
    if (promotion.isPresent()) {
      return promotion.get();
    }
    return null;
  }

  public PromotionRequest getPromotionRequetById(UUID id) {
    Optional<Promotion> response = respository.findById(id);
    if (response.isPresent()) {
      PromotionRequest promotion = new PromotionRequest();
      promotion.setStartTime(response.get().getStartTime());
      promotion.setEndTime(response.get().getEndTime());
      promotion.setValue(response.get().getValue());
      promotion.setId(response.get().getId());

      promotion.setStatus(response.get().getStatus());
      return promotion;
    } else {
      return null;
    }
  }

  public Promotion savePromotion(PromotionRequest promotion) {
    Promotion promotion1 = new Promotion();
    promotion1.setStartTime(promotion.getStartTime());
    promotion1.setEndTime(promotion.getEndTime());
    promotion1.setStatus(promotion.getStatus());
    promotion1.setValue(promotion.getValue());
    promotion1.setDeleted(false);
    Promotion savepromotion = respository.save(promotion1);
    return savepromotion;
  }

  public void deletePromotion(UUID id) {
    Optional<Promotion> promotion = respository.findById(id);
    if (promotion.isPresent()) {
      if (promotion.get().isDeleted()) {
        promotion.get().setDeleted(false);
      } else {
        promotion.get().setDeleted(true);
      }
      respository.save(promotion.get());
    }
  }

  // manager
  public Promotion updatePromotion(PromotionRequest request) {
    Optional<Promotion> promotion = respository.findById(request.getId());
    if (promotion.isPresent()) {
      Promotion promotion1 = promotion.get();
      promotion1.setStartTime(request.getStartTime());
      promotion1.setEndTime(request.getEndTime());
      promotion1.setStatus(request.getStatus());
      promotion1.setValue(request.getValue());
      promotion1.setDeleted(false);
      Promotion savepromotion = respository.save(promotion1);
      return savepromotion;
    } else {
      return null;
    }
  }

  // user
  @Override
  public List<Promotion> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > getPageNumber(pageSize)) {
      return null;
    }
    List<Promotion> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    Page<Promotion> page = respository.findAll(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<Promotion> page = respository.findAll(pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<Promotion> page = respository.findAll(pageable); // findAll()
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
