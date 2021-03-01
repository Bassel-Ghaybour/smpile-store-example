package com.madis.products.database.repositories;

import com.madis.products.database.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
