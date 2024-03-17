package com.smashtaps.springService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smashtaps.springService.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findByProductId(String productId);
}
