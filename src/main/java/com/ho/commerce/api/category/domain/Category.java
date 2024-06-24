package com.ho.commerce.api.category.domain;

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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    private String delYn;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @PrePersist // 새로운 엔티티에 대해 Persist가 호출 되기 전
    public void setPrePersist() {
        this.delYn = "N";
    }

}
