package com.ninjaone.backendinterviewproject.dto;

import lombok.Data;

@Data
public class SvcCostDto {
    private String ServiceId;
    private Long totalCost;

    public SvcCostDto(String serviceId, Long totalCost) {
        ServiceId = serviceId;
        this.totalCost = totalCost;
    }
}