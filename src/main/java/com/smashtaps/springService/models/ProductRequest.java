package com.smashtaps.springService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	private String productId;
	private String category;
	private String brand;
}
