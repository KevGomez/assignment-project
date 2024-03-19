package com.smashtaps.springService.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smashtaps.springService.models.LoginResponse;
import com.smashtaps.springService.models.PersonalizedData;
import com.smashtaps.springService.models.Product;
import com.smashtaps.springService.models.ProductRequest;
import com.smashtaps.springService.models.ResponseModel;
import com.smashtaps.springService.models.Shopper;
import com.smashtaps.springService.service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api/main")
public class MainController {
	
	private static final Logger logger = LogManager.getLogger(MainController.class);
	
	@Autowired
    private MainService mainService;
	

	/*
     * To test the API
     * @Auth Required
     * @Out Confirmation message
     * 
     * */
	@GetMapping("/test")
    public String test() {
    	return "Success Test";
        
    }
	
	/*
     * To save new products to the database
     * @Auth Required
     * @In Product Info
     * @Out Confirmation
     * 
     * */
	@PostMapping("/product-metadata")
    public ResponseEntity<?> saveProduct(@RequestBody Product req) {
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "") + " ";
    	
    	try {
    		logger.info(uuid + " MainController: saveProduct Method Called...ProductID: " + req.getProductId());
    		Product prod = mainService.saveProduct(uuid, req);
    		
    		return ResponseEntity.ok(new LoginResponse("0", "Success", "Product Created: " + prod.getProductId()));
    	}catch(Exception e) {
    		logger.error(uuid + ": ERROR: MainController saveProduct: ");
    		e.printStackTrace();
    		
    		return ResponseEntity.ok(new LoginResponse("1", "Failed", "Cannot Create Product: " + req.getProductId()));
    	}
        
    }
	
	
	/*
     * To save new shoppers to the database
     * @Auth Required
     * @In Shopper Info
     * @Out Confirmation
     * 
     * */
	@PostMapping("/shop-metadata")
    public ResponseEntity<?> saveShopper(@RequestBody Shopper req) {
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "") + " ";
    	
    	try {
    		logger.info(uuid + " MainController: saveShopper Method Called...ShopperID: " + req.getShopperId());
    		Shopper prod = mainService.saveShopper(uuid, req);
    		
    		return ResponseEntity.ok(new LoginResponse("0", "Success", "Shopper Created: " + prod.getShopperId()));
    	}catch(Exception e) {
    		logger.error(uuid + ": ERROR: MainController saveProduct: ");
    		e.printStackTrace();
    		
    		return ResponseEntity.ok(new LoginResponse("1", "Failed", "Cannot Create Shopper: " + req.getShopperId()));
    	}
        
    }
	
	/*
     * To save personalized data based on ShopperID
     * @Auth Required
     * @In product info + shopper ID
     * @Out Confirmation
     * 
     * */
	@PostMapping("/personalized-data")
    public ResponseEntity<?> savePersonalizedData(@RequestBody PersonalizedData req) {
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "") + " ";
    	
    	try {
    		logger.info(uuid + " MainController: savePersonalizedData Method Called...ShopperID: " + req.getShopperID());
    		LoginResponse loginResponse = mainService.savePersonalizedData(uuid, req);
    		return ResponseEntity.ok(loginResponse);
    	}catch(Exception e) {
    		logger.error(uuid + ": ERROR: MainController saveProduct: ");
    		e.printStackTrace();
    		
    		return ResponseEntity.ok(new LoginResponse("1", "Failed", "Cannot Process Personalized Data: : " + req.getShopperID()));
    	}
        
    }
	
	/*
     * To search products based on shopper
     * @Auth Required
     * @Params shopperId & filters
     * @Out Products Info
     * @Caching Active for fast processing
     * @Async Active for fast processing
     * 
     * */
	@GetMapping("/products")
    public ResponseModel getProducts(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") int limit
    ) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "") + " ";
		try {
			if(shopperId.isEmpty() || shopperId == null) {
				return new ResponseModel("1", "Failed", "Shopper ID required!");
			}
			else {
				logger.info(uuid + " MainController: savePersonalizedData Method Called...ShopperID: " + shopperId);
				List<Product> getList = mainService.getProducts(uuid, shopperId, category, brand, limit);
				if(getList != null) {
					return new ResponseModel("0", "Success", getList);
				}
				else {
					return new ResponseModel("0", "Success - No Data", null);
				}
			}
		}catch(Exception e) {
			logger.error(uuid + ": ERROR: MainController getProducts: ");
    		e.printStackTrace();
    		return new ResponseModel("1", "Failed", null);
		}
        
    }
}
