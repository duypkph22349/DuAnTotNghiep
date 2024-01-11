package datn.goodboy.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import datn.goodboy.model.entity.Promotion;

/**
 * PromotionRespository
 */
public interface PromotionRespository extends JpaRepository< Promotion , UUID> {

}
