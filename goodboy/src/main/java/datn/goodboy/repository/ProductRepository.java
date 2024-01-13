package datn.goodboy.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Product;
import datn.goodboy.model.request.ProductFillterManager;
import datn.goodboy.model.request.ProductFilter;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        @Query("SELECT b FROM Product b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
        Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

        Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);

        @Query("SELECT new map(e.id as key, e.name as value) FROM Product e")
        List<Map<Integer, String>> getComboBoxMap();

        @Query("SELECT p FROM Product p WHERE SIZE(p.productDetails) > 0 AND  p.deleted = false")
        Page<Product> getProductSales(Pageable pageable);

        // @Query("SELECT p FROM Product p WHERE SIZE(p.productDetails) > 0 AND
        // p.deleted = false")
        // Page<Product> filter(ProductFilter filter, Pageable pageable);

        @Query("SELECT p FROM Product p WHERE SIZE(p.productDetails) > 0 AND  p.deleted = false")

        Page<Product> getProductSalesByPriceAsc(Pageable pageable);

        @Query("SELECT p FROM Product p WHERE SIZE(p.productDetails) > 0 AND  p.deleted = false")

        Page<Product> getProductSalesByPriceDesc(Pageable pageable);

        Page<Product> findAll(Pageable pageable);

        @Query("SELECT p FROM Product p " +
                        "WHERE (:selectedBrands IS NULL OR p.idBrand.id IN :selectedBrands) " +
                        "AND (:selectedScarfTypes IS NULL OR p.idStyles.id IN :selectedScarfTypes) " +
                        "AND (:selectedColors IS NULL OR p.idColor.id IN :selectedColors)")
        List<Product> findByFilters(@Param("selectedBrands") List<Long> selectedBrands,
                        @Param("selectedScarfTypes") List<Long> selectedScarfTypes,
                        @Param("selectedColors") List<Long> selectedColors);

        @Query("SELECT p FROM Product p " +
                        "WHERE " +
                        "(LOWER(p.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idOrigin.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idBrand.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idMaterial.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idCategory.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idStyles.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%')) " +
                        "AND (:#{#filter.idOrigins} IS NULL OR p.idOrigin.id IN :#{#filter.idOrigins}) " +
                        "AND (:#{#filter.idBrands} IS NULL OR p.idBrand.id IN :#{#filter.idBrands}) " +
                        "AND (:#{#filter.idMaterials} IS NULL OR p.idMaterial.id IN :#{#filter.idMaterials}) " +
                        "AND (:#{#filter.idStyless} IS NULL OR p.idStyles.id IN :#{#filter.idStyless}) " +
                        "AND (:#{#filter.idCategorys} IS NULL OR p.idCategory.id IN :#{#filter.idCategorys}) " +
                        "AND SIZE(p.productDetails) > 0 AND p.deleted = false")
        Page<Product> filter(@Param("filter") ProductFilter filter, Pageable pageable);

        @Query("SELECT p FROM Product p " +
                        "WHERE " +
                        "(LOWER(p.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idOrigin.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idBrand.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idMaterial.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idCategory.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "OR LOWER(p.idStyles.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%')) " +
                        "AND (:#{#filter.idOrigins} IS NULL OR p.idOrigin.id IN :#{#filter.idOrigins}) " +
                        "AND (:#{#filter.idBrands} IS NULL OR p.idBrand.id IN :#{#filter.idBrands}) " +
                        "AND (:#{#filter.idMaterials} IS NULL OR p.idMaterial.id IN :#{#filter.idMaterials}) " +
                        "AND (:#{#filter.idStyless} IS NULL OR p.idStyles.id IN :#{#filter.idStyless}) " +
                        "AND (:#{#filter.idCategorys} IS NULL OR p.idCategory.id IN :#{#filter.idCategorys}) " +
                        "AND SIZE(p.productDetails) > 0 AND p.deleted = false")
        List<Product> filterAll(@Param("filter") ProductFilter filter);

        @Query("SELECT p FROM Product p WHERE SIZE(p.productDetails) > 0 AND  p.deleted = false")
        List<Product> getProductSales();

        @Query(value = """
                        SELECT p.id
                        FROM [bill_detail] bd
                        JOIN [bill] b ON b.[id] = bd.[id_bill]
                        JOIN [product_detail] pd ON pd.[id] = bd.[id_product_detail]
                        JOIN [product] p ON p.[id] = pd.[id_product]
                        WHERE (b.[created_at] BETWEEN :dateFrom AND :dateTo)
                        GROUP BY p.id, p.name
                        ORDER BY SUM(bd.[total_money]) DESC
                        """, nativeQuery = true)
        Page<Long> getTopProductsSale(
                        @Param("dateFrom") LocalDateTime dateFrom,
                        @Param("dateTo") LocalDateTime dateTo,
                        Pageable pageable);

        @Query("SELECT p FROM Product p " +
                        "WHERE " +
                        "  ( LOWER(p.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "   OR LOWER(p.idOrigin.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "   OR LOWER(p.idBrand.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "   OR LOWER(p.idMaterial.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "   OR LOWER(p.idStyles.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') " +
                        "   OR LOWER(p.idCategory.name) LIKE CONCAT('%', LOWER(:#{#filter.txtSearch}), '%') )" +
                        "AND (:#{#filter.idOrigin} = -1 OR p.idOrigin.id = :#{#filter.idOrigin})" +
                        "AND (:#{#filter.idCategory} = -1 OR p.idCategory.id = :#{#filter.idCategory}) " +
                        "AND (:#{#filter.idBrand} = -1 OR p.idBrand.id = :#{#filter.idBrand}) " +
                        "AND (:#{#filter.idMaterial} = -1 OR p.idMaterial.id = :#{#filter.idMaterial}) " +
                        "AND (:#{#filter.idStyles} = -1 OR p.idStyles.id = :#{#filter.idStyles})")
        Page<Product> filter(@Param("filter") ProductFillterManager filter, Pageable pageable);

}
