package datn.goodboy.repository;

<<<<<<< HEAD

import datn.goodboy.model.entity.Brand;
=======
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
import datn.goodboy.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
<<<<<<< HEAD
//    Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);
=======
    @Query("SELECT b FROM Product b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
}
