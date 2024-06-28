package com.ho.commerce.api.optionvalue.domain;

import com.ho.commerce.api.productoption.domain.ProductOption;
import jakarta.persistence.*;

@Table(name = "OPTION_VALUE")
@Entity
public class OptionValue {

    @Id
    @Column(name = "OPTION_VALUE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionValueId;

    @ManyToOne
    @JoinColumn(name = "productOptionId")
    private ProductOption productOption;

    private String value;
    private Integer additionalPrice;

}