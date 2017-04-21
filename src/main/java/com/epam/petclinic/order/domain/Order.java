package com.epam.petclinic.order.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pc_order", schema = "app")
public class Order implements Serializable {
    @Id
    @Column(name = "pc_order_id")
    private String id;
    @Column(name = "pc_name")
    private String name;
    @Column(name = "pc_email")
    private String email;
    @Column(name = "pc_address")
    private String address;
    @Column(name = "pc_animal_id")
    private String animalId;
    @ElementCollection
    @CollectionTable(
            name = "service_to_order_map", schema = "app",
            joinColumns = @JoinColumn(name = "pc_order_id")
    )
    @Column(name = "pc_service_id")
    private List<String> serviceIds;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public List<String> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<String> serviceIds) {
        this.serviceIds = serviceIds;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || this.getClass() != obj.getClass()) {
            return false;
        }
        final Order that = (Order) obj;
        return new EqualsBuilder()
                .append(this.id, that.id)
                .append(this.name, that.name)
                .append(this.email, that.email)
                .append(this.address, that.address)
                .append(this.animalId, that.animalId)
                .append(this.serviceIds, that.serviceIds)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.name)
                .append(this.email)
                .append(this.address)
                .append(this.animalId)
                .append(this.serviceIds)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", this.name)
                .append("email", this.email)
                .append("address", this.address)
                .append("animalId", this.animalId)
                .append("serviceIds", this.serviceIds)
                .toString();
    }
}
