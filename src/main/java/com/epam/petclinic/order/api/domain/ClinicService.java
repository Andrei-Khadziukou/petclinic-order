package com.epam.petclinic.order.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClinicService implements Serializable {
    private String id;
    private String name;
}
