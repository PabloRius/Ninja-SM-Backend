package com.sm_ninja.sm_ninja.controller;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sm_ninja.sm_ninja.model.PagedResponse;
import com.sm_ninja.sm_ninja.model.Product;


@RestController
@RequestMapping("/dev/product")
public class DevProductController {
    private List<Product> products;

    public DevProductController() throws Exception {
        try(Reader reader = new FileReader("resources/products.csv")) {
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
                                                .withType(Product.class)
                                                .withIgnoreLeadingWhiteSpace(true)
                                                .build();
            this.products = csvToBean.parse();
        }
    }

    @GetMapping
    public PagedResponse<Product> getProducts(
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String supermarket,
                @RequestParam(required = false) Double minPrice,
                @RequestParam(required = false) Double maxPrice,
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "20") int limit) {
                    // Filter products based on the provided criteria
                    List<Product> filteredProducts = products.stream()
                            .filter(p -> (name == null || p.getName().toLowerCase().contains(name.toLowerCase())))
                            .filter(p -> (supermarket == null || p.getSupermarket().toLowerCase().contains(supermarket.toLowerCase())))
                            .filter(p -> (minPrice == null || p.getPrice() >= minPrice))
                            .filter(p -> (maxPrice == null || p.getPrice() <= maxPrice))
                            .collect(Collectors.toList());
                
                    // Calculate pagination indices
                    int startIndex = (page - 1) * limit;
                    int endIndex = Math.min(startIndex + limit, filteredProducts.size());
                
                    // Handle invalid page or limit values
                    if (startIndex >= filteredProducts.size() || startIndex < 0) {
                        return new PagedResponse<>(List.of(), page, limit, filteredProducts.size());
                    }
                
                    // Get the paginated subset of products
                    List<Product> paginatedProducts = filteredProducts.subList(startIndex, endIndex);
                
                    // Return the paginated response with metadata
                    return new PagedResponse<>(paginatedProducts, page, limit, filteredProducts.size());
                }
    
}
