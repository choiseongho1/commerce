package com.ho.commerce.api.order.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;

@Embeddable
@Builder
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}