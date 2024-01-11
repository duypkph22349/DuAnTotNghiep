package datn.goodboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.repository.PromotionDetailRespository;

/**
 * PromotionDetailService
 */
@Service
public class PromotionDetailService {
  @Autowired
  PromotionDetailRespository respository;
}
