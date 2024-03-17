package com.smashtaps.springService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smashtaps.springService.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
