package com.ho.commerce.api.productoption.domain;

import com.ho.commerce.api.optionvalue.domain.OptionValue;
import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOptionId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private String name;

    @OneToMany(mappedBy = "productOption")
    private List<OptionValue> optionValues;

}