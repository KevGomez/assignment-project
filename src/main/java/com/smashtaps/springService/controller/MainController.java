package com.smashtaps.springService.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.smashtaps.springService.models.LoginResponse;
import com.smashtaps.springService.models.Product;
import com.smashtaps.springService.models.ProductRequest;
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
	
	private static final Logger logger = LogManager.getLogger(AuthController.class);
	
	@Autowired
    private MainService mainService;
	

	@GetMapping("/test")
    public String test() {
    	return "Success Test";
        
    }
	
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
}
