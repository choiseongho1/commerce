package com.ho.commerce.api.productaddon.domain;

import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productAddonId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ADDON_PRODUCT_ID")
    private Product addonProduct;
}
