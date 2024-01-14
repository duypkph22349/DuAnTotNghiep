package datn.goodboy.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import datn.goodboy.model.entity.PromotionDetail;

/**
 * PromotionDetailRespository
 */
public interface PromotionDetailRespository extends JpaRepository<PromotionDetail , UUID > {

}
