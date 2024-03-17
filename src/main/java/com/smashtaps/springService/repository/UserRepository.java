package com.smashtaps.springService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smashtaps.springService.models.RequestUser;

public interface UserRepository extends JpaRepository<RequestUser, Long> {
	RequestUser findByUsername(String username);
}
