package com.epam.petclinic.order.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Clinic implements Serializable {
    private String id;
    private String name;
    private String address;
    private List<Offer> offers;
}
