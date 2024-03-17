package com.smashtaps.springService.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smashtaps.springService.models.Product;
import com.smashtaps.springService.models.Shopper;
import com.smashtaps.springService.repository.ProductRepository;
import com.smashtaps.springService.repository.ShopperRepository;

@Service
public class MainService {
	private static final Logger logger = LogManager.getLogger(AuthService.class);

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ShopperRepository shopperRepository;
    
    public Product saveProduct(String uuid, Product product) {
    	try {
    		logger.info(uuid + " MainService: saveProduct Method Called...ProductID: " + product.getProductId());
            return productRepository.save(product);
    	}catch(Exception e) {
    		logger.error(uuid + ": ERROR: MainService saveProduct: " + product.getProductId());
    		e.printStackTrace();
    		throw e;
    	}
        
    }
    
    public Shopper saveShopper(String uuid, Shopper shop) {
    	try {
    		logger.info(uuid + " MainService: saveShopper Method Called...ShopperID: " + shop.getShopperId());
            return shopperRepository.save(shop);
    	}catch(Exception e) {
    		logger.error(uuid + ": ERROR: MainService saveShopper: " + shop.getShopperId());
    		e.printStackTrace();
    		throw e;
    	}
        
    }

}
