package com.smashtaps.springService.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalizedData {

	private String shopperID;
	private List<ShelfItem> shelf;
	
	@Getter
	@Setter
	public static class ShelfItem{
		private String productId;
		private Double relevancyScore;
	}
}


