package com.example.cntr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ProductRepository;
import com.example.dto.Product;



@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/product_add")
	public String productAdd(@RequestBody Product product) {
		productRepository.save(product);
		return "success";
	}
	
	@DeleteMapping("/product_delete/{productId}") 
	public String productDelete(@PathVariable int productId) {
		productRepository.deleteById(productId);
		return "success";
	}
	
	@PutMapping("/product_update")
	public String productUpdate(@RequestBody Product product) {
		productRepository.save(product);
		return "success";
	}
	
	@GetMapping("/product_list")
	public List<Product> productList(){
		Iterable<Product> itr =  productRepository.findAll();
		Iterator<Product> it = itr.iterator();
		
		List<Product> prodList = new ArrayList<>();
		
		while(it.hasNext()) {
			prodList.add(it.next());
		}
		
		return prodList;
	}
	
	@GetMapping("/product_select")
	public Product productSelect(@RequestParam int productId) {
		Optional<Product> prod = productRepository.findById(productId);
		return prod.get();
	}
	
}
