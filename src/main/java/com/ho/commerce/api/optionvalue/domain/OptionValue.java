package com.ho.commerce.api.optionvalue.domain;

import com.ho.commerce.api.productoption.domain.ProductOption;
import jakarta.persistence.*;

@Entity
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionValueId;

    @ManyToOne
    @JoinColumn(name = "productOptionId")
    private ProductOption productOption;

    private String value;
    private Integer additionalPrice;

}