package com.sm_ninja.sm_ninja.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.sm_ninja.sm_ninja.model.Product;
import com.sm_ninja.sm_ninja.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService; 
    
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getProduct() {
        return productService.getProducts();
    }
    
}
