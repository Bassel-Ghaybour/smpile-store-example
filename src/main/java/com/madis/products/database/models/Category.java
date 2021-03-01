package com.madis.products.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.madis.products.domain.CategoryToD;
import lombok.Data;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name = "Category: " + RandomString.make(3);

    private final Date creationTimestamp = Calendar.getInstance().getTime();


    @OneToMany(fetch = FetchType.LAZY)
    private Set<Product> products;

    public Category copy(Category category) {
        if (this != category) {
            this.name = category.name;
        }

        return this;
    }

    public CategoryToD toDomain() {
        return new CategoryToD(this.id, this.name, this.creationTimestamp);
    }
}
