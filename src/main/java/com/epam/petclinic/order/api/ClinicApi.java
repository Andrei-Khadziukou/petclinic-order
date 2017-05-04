package com.epam.petclinic.order.api;

import com.epam.petclinic.order.api.domain.Clinic;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@FeignClient(
        qualifier = "api.clinic", // name of the bean in the Spring context
        name = "CLINIC-SERVICE" // name of the service to connect
)
public interface ClinicApi {
    @RequestMapping(value = "/clinics/", method = RequestMethod.GET)
    Collection<Clinic> getClinics();

    @RequestMapping(value = "/clinics/by-animal-services", method = RequestMethod.GET)
    Collection<UUID> getClinicIdByAnimalIdAndServiceIds(@RequestParam("animalId") UUID animalId,
                                                        @RequestParam("serviceIds") List<UUID> serviceIds);
}
