package com.ho.commerce.api.optiongroup.domain;

import com.ho.commerce.api.option.domain.Option;
import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OptionGroupId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @OneToMany(mappedBy = "optionGroup")
    private List<Option> option;
}