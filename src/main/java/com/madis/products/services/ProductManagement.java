package com.madis.products.services;

import com.madis.products.database.models.Category;
import com.madis.products.database.models.Product;
import com.madis.products.database.repositories.CategoryRepository;
import com.madis.products.database.repositories.ProductRepository;
import com.madis.products.domain.CategoryToD;
import com.madis.products.domain.ProductToD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManagement {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @PostConstruct
    private void flushDatabase() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        var categories = (List<Category>) categoryRepository.saveAll(
                new Random().ints(5)
                        .mapToObj(value -> new Category())
                        .collect(Collectors.toList()));

        categories.stream()
                .map(category -> {
                    var products = new Random().ints(5)
                            .mapToObj(value -> new Product())
                            .collect(Collectors.toList());
                    products.forEach(product -> product.setCategory(category));
                    return products;
                })
                .forEach(productRepository::saveAll);

    }


    public List<ProductToD> fetchAllProducts() {
        return ((List<Product>) productRepository.findAll())
                .stream().map(Product::toDomain)
                .collect(Collectors.toList());
    }

    public List<CategoryToD> fetchAllCategories() {
        return ((List<Category>) categoryRepository.findAll())
                .stream().map(Category::toDomain).collect(Collectors.toList());
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product editExistProduct(Product product) {
        var optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            var editable = optionalProduct.get();
            return productRepository.save(editable.copy(product));
        }
        return null;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category editExistCategory(Category category) {
        var optionalCategory = categoryRepository.findById(category.getId());

        if (optionalCategory.isPresent()) {
            var editableCategory = optionalCategory.get();
            return categoryRepository.save(editableCategory.copy(category));
        }

        return null;
    }


}
