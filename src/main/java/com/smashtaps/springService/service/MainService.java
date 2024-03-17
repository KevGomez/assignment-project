package com.smashtaps.springService.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smashtaps.springService.models.LoginResponse;
import com.smashtaps.springService.models.PersonalizedData;
import com.smashtaps.springService.models.PersonalizedData.ShelfItem;
import com.smashtaps.springService.models.Product;
import com.smashtaps.springService.models.Shopper;
import com.smashtaps.springService.models.ShopperProduct;
import com.smashtaps.springService.repository.ProductRepository;
import com.smashtaps.springService.repository.ShopperProductRepository;
import com.smashtaps.springService.repository.ShopperRepository;

@Service
public class MainService {
	private static final Logger logger = LogManager.getLogger(AuthService.class);

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ShopperRepository shopperRepository;
    
    @Autowired
    private ShopperProductRepository shopperProductRepository;
    
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
    
    public LoginResponse savePersonalizedData(String uuid, PersonalizedData req) {
    	try {
    		logger.info(uuid + " MainService: savePersonalizedData Method Called...ShopperID: " + req.getShopperID());
    		Shopper shopper = shopperRepository.findByShopperId(req.getShopperID());
    		
    		if(shopper != null) {
    			for(ShelfItem item : req.getShelf()) {
    				Product product = productRepository.findByProductId(item.getProductId());
    				if(product != null) {
    					ShopperProduct shopperProduct = new ShopperProduct();
    					shopperProduct.setShopper(shopper);
    					shopperProduct.setProduct(product);
    					shopperProduct.setRelevancyScore(item.getRelevancyScore());
    					
    					ShopperProduct shoppers = shopperProductRepository.save(shopperProduct);
    				}
    				else {
    					return new LoginResponse("1", "Failed", "Invalid Product: " + item.getProductId());
    				}
    			}
    			return new LoginResponse("0", "Success", "Personalized Data Processed for : " + req.getShopperID());
    		}
    		else {
    			return new LoginResponse("1", "Failed", "Invalid Shopper");
    		}
    		
    	}catch(Exception e) {
    		logger.error(uuid + ": ERROR: MainService save Personalized Data: " + req.getShopperID());
    		e.printStackTrace();
    		return new LoginResponse("1", "Failed", "Personalized Data Processing Failed!");
    	}
        
    }
    
    public List<Product> getProducts(String uuid, String shopperId, String category, String brand, int limit) {
    	try {
    		Pageable pageable = PageRequest.of(0, limit);
    		Shopper shopper = shopperRepository.findByShopperId(shopperId);
            return shopperProductRepository.findProductsByShopperIdAndCategoryAndBrand(shopper.getId(), category, brand, pageable);
    	}catch(Exception e) {
    		logger.error(uuid + ": ERROR: MainService Getting getProducts: " + shopperId);
    		e.printStackTrace();
    		return null;
    	}
        
    }

}
