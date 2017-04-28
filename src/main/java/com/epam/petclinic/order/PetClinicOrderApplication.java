package com.epam.petclinic.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PetClinicOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicOrderApplication.class, args);
    }
}
