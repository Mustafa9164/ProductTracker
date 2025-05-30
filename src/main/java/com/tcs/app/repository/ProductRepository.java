package com.tcs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
