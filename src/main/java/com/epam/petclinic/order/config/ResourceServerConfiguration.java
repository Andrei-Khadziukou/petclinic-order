package com.epam.petclinic.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Configures access to service resources.
 *
 * Date: 5/19/2017
 *
 * @author Andrei Astaschenko
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            // example of adding request security
            .antMatchers(HttpMethod.GET, "/orders").authenticated()
            .and().authorizeRequests()
            .antMatchers(HttpMethod.GET, "/orders/clinic-test").hasAuthority("admin")
            .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // TODO: move resource configuration to external properties.
        resources.resourceId("ORDER-SERVICE");
    }
}
