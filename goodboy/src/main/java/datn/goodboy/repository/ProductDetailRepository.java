package datn.goodboy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.ProductDetailFilter;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
        @Query("SELECT b FROM ProductDetail b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
        Page<ProductDetail> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

        Page<ProductDetail> findAllByOrderByCreatedAtDesc(Pageable pageable);

        @Query("SELECT entity FROM ProductDetail entity " +
                        "WHERE (LOWER(entity.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idProduct.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idPattern.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        // "OR LOWER(entity.idColor.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idProduct.idOrigin.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idProduct.idBrand.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idProduct.idMaterial.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idSize.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idProduct.idStyles.name) LIKE CONCAT('%', LOWER(:txt), '%'))")
        Page<ProductDetail> searchByText(@Param("txt") String txtSearch, Pageable pageable);

        @Query("SELECT pd FROM ProductDetail pd WHERE " +
                        "(LOWER(pd.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idProduct.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idPattern.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        // "OR LOWER(pd.idColor.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}),
                        // '%') " +
                        "OR LOWER(pd.idProduct.idOrigin.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idProduct.idBrand.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idProduct.idMaterial.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idSize.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idProduct.idStyles.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%'))" +
                        "AND (:#{#filter.idProduct} = -1 OR pd.idProduct.id = :#{#filter.idProduct}) " +
                        "AND (:#{#filter.idPattern} = -1 OR pd.idPattern.id = :#{#filter.idPattern}) " +
                        // "AND (:#{#filter.idColor} = -1 OR pd.idProduct.idColor.id =
                        // :#{#filter.idColor}) " +
                        "AND (:#{#filter.idOrigin} = -1 OR pd.idProduct.idOrigin.id = :#{#filter.idOrigin}) " +
                        "AND (:#{#filter.idBrand} = -1 OR pd.idProduct.idBrand.id = :#{#filter.idBrand}) " +
                        "AND (:#{#filter.idMaterial} = -1 OR pd.idProduct.idMaterial.id = :#{#filter.idMaterial}) " +
                        "AND (:#{#filter.idSize} = -1 OR pd.idSize.id = :#{#filter.idSize}) " +
                        "AND (:#{#filter.idStyles} = -1 OR pd.idProduct.idStyles.id = :#{#filter.idStyles})")
        Page<ProductDetail> filter(@Param("filter") ProductDetailFilter filter, Pageable pageable);

        Page<ProductDetail> findAll(Pageable pageable);

        @Query("SELECT pd FROM ProductDetail pd WHERE pd.id = :id")
        Optional<ProductDetail> getProductByLongId(@Param("id") Long id);

        @Query("SELECT pd FROM ProductDetail pd WHERE pd.id = :id")
        ProductDetail findProductByLongId(Integer id);

}
