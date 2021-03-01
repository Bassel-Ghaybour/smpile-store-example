package com.madis.products.domain;

import com.madis.products.database.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductToD {


    private Long id;

    private String name;

    private Date creationDate;

    private Long categoryId;

    private String categoryName;

}
