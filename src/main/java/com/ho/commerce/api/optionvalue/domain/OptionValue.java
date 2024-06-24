package com.ho.commerce.api.optionvalue.domain;

import com.ho.commerce.api.productoption.domain.ProductOption;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionValueId;

    @ManyToOne
    @JoinColumn(name = "productOptionId")
    private ProductOption productOption;

    private String value;
    private BigDecimal additionalPrice;

}