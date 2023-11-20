package datn.goodboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datn.goodboy.model.entity.PayDetail;
import datn.goodboy.model.entity.PayDetailId;

public interface PayDetailRepository extends JpaRepository<PayDetail, PayDetailId> {

}
