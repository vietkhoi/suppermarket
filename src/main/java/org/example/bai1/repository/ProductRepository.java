package org.example.bai1.repository;

import org.example.bai1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByName(String name);
    public List<Product> findByNameContaining(String name);
    public List<Product> findByNameContainingOrPrice(String name,Double price);

    @Query("SELECT p from Product p "+
            "WHERE (:name is NULL OR p.name LIKE %:name%) "+
            "AND (:minPrice is NULL OR p.price >= :minPrice) "+
            "AND (:maxPrice is NULL OR p.price <= :maxPrice) "
    )

    public List<Product> search(@Param("name") String name,
                                @Param("minPrice") Double minPrice,
                                @Param("maxPrice") Double maxPrice
                                );
}
