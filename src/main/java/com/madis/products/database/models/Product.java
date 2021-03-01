package com.madis.products.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.madis.products.domain.ProductToD;
import lombok.Data;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name = "Product: " + RandomString.make(3);

    private final Date creationTimestamp = Calendar.getInstance().getTime();

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public Product copy(Product product) {

        if (this != product) {
            this.category = product.category;
            this.name = product.name;
        }

        return this;
    }


    public ProductToD toDomain() {
        return new ProductToD(this.id, this.name, this.creationTimestamp, this.category.getId(), this.category.getName());
    }

}
