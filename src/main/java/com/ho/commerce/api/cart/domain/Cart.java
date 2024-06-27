package com.ho.commerce.api.cart.domain;

import com.ho.commerce.api.cartitem.domain.CartItem;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart extends BaseTimeEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @Override
    public Long getId() {
        return this.cartId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }
}