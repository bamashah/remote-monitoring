package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Svc {
    @Id
    private String serviceId;
    private Long cost;
    private String displayValue;

    public Svc(){}
    public Svc(String serviceId, Long cost, String displayValue) {
        this.serviceId = serviceId;
        this.cost = cost;
        this.displayValue = displayValue;
    }

    public String getServiceId() {
        return serviceId;
    }

    public Long getCost() {
        return cost;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
