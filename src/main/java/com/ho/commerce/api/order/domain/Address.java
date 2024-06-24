package com.ho.commerce.api.order.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}