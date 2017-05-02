package com.epam.petclinic.order.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Animal implements Serializable {
    private String id;
    private String name;
}
