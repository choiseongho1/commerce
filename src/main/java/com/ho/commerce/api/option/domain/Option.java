package com.ho.commerce.api.option.domain;

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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;
    private Integer additionalPrice;
}
