package com.ho.commerce.api.cartitem.domain;

import com.ho.commerce.api.cart.domain.Cart;
import com.ho.commerce.api.option.domain.Option;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CartItem extends BaseTimeEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "optionId")
    private Option option;

    private Integer quantity;

    @Override
    public Long getId() {
        return this.cartItemId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }
}