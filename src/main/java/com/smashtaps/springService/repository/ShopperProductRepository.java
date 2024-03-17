package com.smashtaps.springService.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smashtaps.springService.models.Product;
import com.smashtaps.springService.models.ShopperProduct;

@Repository
public interface ShopperProductRepository extends JpaRepository<ShopperProduct, Long>{

	@Query("SELECT sp.product FROM ShopperProduct sp WHERE sp.shopper.id = :shopperId " +
		       "AND (:category IS NULL OR :category = '' OR sp.product.category = :category) " +
		       "AND (:brand IS NULL OR :brand = '' OR sp.product.brand = :brand) " +
		       "AND sp.relevancyScore > :maxRelevancyScore " +
		       "ORDER BY sp.relevancyScore DESC")
		List<Product> findProductsByShopperIdAndCategoryAndBrand(Long shopperId, String category, String brand, Double maxRelevancyScore, Pageable pageable);

	
}
