package com.tcs.app.controller;

import java.util.List;
import java.util.Optional;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
	
	@GetMapping("/editProduct/{id}")
	public String showEdit(Model model,@PathVariable("id") Integer pid) {
		 Optional<Product> optional = repository.findById(pid);
		 if(optional.isPresent()) {
				model.addAttribute("product", optional.get());
				return "editProduct";
		 }
		
		return "errorPage";
	}
	
	@PostMapping("/updateProduct")
	public String handleUpdateProd(@ModelAttribute Product product,Model model) {
			Product save = repository.save(product);
			List<Product> allProd = repository.findAll();
			model.addAttribute("products", allProd);
			return "data";
		 }
	
	@GetMapping("/deleteProduct/{id}")
	public String handleDeleteProd(@PathVariable("id") Integer pid,Model model) {
		repository.deleteById(pid);
		List<Product> allProd = repository.findAll();
		model.addAttribute("products", allProd);
		return "data";
	}

}
