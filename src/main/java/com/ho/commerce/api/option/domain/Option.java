package com.ho.commerce.api.option.domain;

import com.ho.commerce.api.optiongroup.domain.OptionGroup;
import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    private String name;
    private Integer additionalPrice;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "OPTION_GROUP_ID")
    private OptionGroup optionGroup;
}
