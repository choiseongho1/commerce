package com.ho.commerce.api.product.domain;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.option.domain.Option;
import com.ho.commerce.api.optiongroup.domain.OptionGroup;
import com.ho.commerce.api.productaddon.domain.ProductAddon;
import com.ho.commerce.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Member seller;

    @OneToMany(mappedBy = "product")
    private List<Option> options;

    @OneToMany(mappedBy = "product")
    private List<OptionGroup> optionGroups;

    @OneToMany(mappedBy = "product")
    private List<ProductAddon> productAddons;
}
