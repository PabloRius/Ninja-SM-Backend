package com.sm_ninja.sm_ninja.service;

import com.sm_ninja.sm_ninja.model.Product;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public List<Product> getProducts(){
        return List.of(
            new Product("Muller Light Red Fruit Assorted Fat Free Yogurt 6X140g",4.0,4.8,"per_kilo","Tesco","https://www.tesco.com/groceries/en-GB/products/311935766","https://digitalcontent.api.tesco.com/v2/media/ghs/cdc7bcd8-5c72-476c-befa-d1b0e2f600a9/4653ae73-a505-4a0f-85f6-6e8d0d1f3b99.jpeg?h=225&w=225"),
            new Product("Muller Light Fat Free Mango Psn/Fruit Peach Yogurt 6X140g",4.0,4.8,"per_kilo","Tesco","https://www.tesco.com/groceries/en-GB/products/311229018","https://digitalcontent.api.tesco.com/v2/media/ghs/71b755b7-03e7-4e79-818f-5451b6463054/668f5795-b895-4ddb-bee3-9a18c216d755.jpeg?h=225&w=225")
        );
    }
}
