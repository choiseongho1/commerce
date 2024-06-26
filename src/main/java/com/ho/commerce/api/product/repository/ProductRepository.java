package com.ho.commerce.api.product.repository;

import com.ho.commerce.api.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, ProductDslRepository {
}
