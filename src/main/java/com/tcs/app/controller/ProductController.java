package com.tcs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tcs.app.entity.Product;
import com.tcs.app.repository.ProductRepository;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping("/products")
	public String getAllProduct(Model model){
		List<Product> allProd = repository.findAll();
		model.addAttribute("products", allProd);
		return "data";
	}
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		Product pObj=new Product();
		model.addAttribute("product",pObj);
		return "index";
	}
	
	@PostMapping("/product")
	public String handleSave(Product product,Model model) {
		Product saveProduct = repository.save(product);
		model.addAttribute("msg", "Product Saved sucessfully");
		return "index";
	}

}
