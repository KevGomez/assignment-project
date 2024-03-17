package com.smashtaps.springService.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@Setter
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String brand;
    
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ShopperProduct> shopperProducts = new HashSet<>();
}

