package datn.goodboy.repository;

<<<<<<< HEAD
import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    Page<Size> findAllByOrderByCreatedAtDesc(Pageable pageable);
=======
public interface SizeRepository {
>>>>>>> master
}
