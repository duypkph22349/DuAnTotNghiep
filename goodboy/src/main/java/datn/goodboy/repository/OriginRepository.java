package datn.goodboy.repository;

<<<<<<< HEAD
import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Origin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer> {
    Page<Origin> findAllByOrderByCreatedAtDesc(Pageable pageable);
=======
public interface OriginRepository {
>>>>>>> master
}
