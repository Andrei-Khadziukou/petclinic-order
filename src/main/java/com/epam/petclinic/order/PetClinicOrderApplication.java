package com.epam.petclinic.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableDiscoveryClient
@EnableEurekaClient
public class PetClinicOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicOrderApplication.class, args);
    }
}
