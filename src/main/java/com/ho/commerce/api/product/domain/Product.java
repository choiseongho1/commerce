package com.ho.commerce.api.product.domain;


import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.orderitem.OrderItem;
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
public class Product  extends BaseTimeEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private String delYn;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member member;

    @PrePersist // 새로운 엔티티에 대해 Persist가 호출 되기 전
    public void setPrePersist() {
        this.delYn = "N";
    }

    @Override
    public Long getId() {
        return this.productId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }
}