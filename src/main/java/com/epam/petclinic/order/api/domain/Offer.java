package com.epam.petclinic.order.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Offer implements Serializable {
    private String id;
    private Clinic clinic;
    private Animal animal;
    private ClinicService clinicService;
}
