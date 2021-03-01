package com.madis.products.contollers;

import com.madis.products.database.models.Product;
import com.madis.products.domain.ProductToD;
import com.madis.products.services.ProductManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductManagement productManagement;

    @GetMapping(path = "/all")
    public List<ProductToD> fetchAllProducts() {
        return productManagement.fetchAllProducts();
    }
}
