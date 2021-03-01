package com.madis.products.database.repositories;

import com.madis.products.database.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
