package datn.goodboy.repository;

import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.ProductDetailFilter;
import datn.goodboy.model.response.ProductDetailResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
        @Query("SELECT b FROM ProductDetail b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
        Page<ProductDetail> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

        Page<ProductDetail> findAllByOrderByCreatedAtDesc(Pageable pageable);

        @Query("SELECT new  datn.goodboy.model.response.ProductDetailResponse(entity.id,entity.code ,entity.name ,entity.price ,entity.quantity ,entity.description ,entity.idProduct.name ,entity.idPattern.name , entity.idColor.name , entity.idOrigin.name , entity.idBrand.name , entity.idMaterial.name , entity.idSize.name , entity.idStyles.name ,entity.status,entity.deleted) FROM ProductDetail entity")
        Page<ProductDetailResponse> getResponsePage(Pageable pageable);

        @Query("SELECT entity FROM ProductDetail entity " +
                        "WHERE (LOWER(entity.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idProduct.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idPattern.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idColor.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idOrigin.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idBrand.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idMaterial.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idSize.name) LIKE CONCAT('%', LOWER(:txt), '%') " +
                        "OR LOWER(entity.idStyles.name) LIKE CONCAT('%', LOWER(:txt), '%'))")
        Page<ProductDetail> searchByText(@Param("txt") String txtSearch, Pageable pageable);

        @Query("SELECT pd FROM ProductDetail pd WHERE " +
                        "(LOWER(pd.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idProduct.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idPattern.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idColor.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idOrigin.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idBrand.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idMaterial.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idSize.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(pd.idStyles.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%'))" +
                        "AND (:#{#filter.idProduct} = -1 OR pd.idProduct.id = :#{#filter.idProduct}) " +
                        "AND (:#{#filter.idPattern} = -1 OR pd.idPattern.id = :#{#filter.idPattern}) " +
                        "AND (:#{#filter.idColor} = -1 OR pd.idColor.id = :#{#filter.idColor}) " +
                        "AND (:#{#filter.idOrigin} = -1 OR pd.idOrigin.id = :#{#filter.idOrigin}) " +
                        "AND (:#{#filter.idBrand} = -1 OR pd.idBrand.id = :#{#filter.idBrand}) " +
                        "AND (:#{#filter.idMaterial} = -1 OR pd.idMaterial.id = :#{#filter.idMaterial}) " +
                        "AND (:#{#filter.idSize} = -1 OR pd.idSize.id = :#{#filter.idSize}) " +
                        "AND (:#{#filter.idStyles} = -1 OR pd.idStyles.id = :#{#filter.idStyles})")
        Page<ProductDetail> filter(@Param("filter") ProductDetailFilter filter, Pageable pageable);

        // ProductDetail findBySlug(String slug);

        // List<ProductDetail> findByCategoryIsMale(boolean gender);
        //
        // List<ProductDetail> findByCategoryId(Integer categoryId , Sort sort);

        Page<ProductDetail> findAll(Pageable pageable);

        // Page<ProductDetail> findByCategoryId(Integer categoryId, Pageable pageable);
}
