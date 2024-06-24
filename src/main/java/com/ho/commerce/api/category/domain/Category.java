package com.ho.commerce.api.category.domain;

import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
