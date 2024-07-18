package com.ho.commerce.api.order.domain;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.orderitem.domain.OrderItem;
import com.ho.commerce.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseTimeEntity implements Persistable<String> {

    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private LocalDateTime orderDate;
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Embedded
    private Address address;

    @Override
    public String getId() {
        return this.orderId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }
}