package com.smashtaps.springService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smashtaps.springService.models.Shopper;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long>{
	Shopper findByShopperId(String shopperId);
}
