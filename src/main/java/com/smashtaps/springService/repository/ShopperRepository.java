package com.smashtaps.springService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smashtaps.springService.models.Shopper;

public interface ShopperRepository extends JpaRepository<Shopper, Long>{
	Optional<Shopper> findByShopperId(String shopperId);
}
