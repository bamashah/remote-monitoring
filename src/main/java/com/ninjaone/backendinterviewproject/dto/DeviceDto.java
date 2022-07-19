package com.ninjaone.backendinterviewproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeviceDto {
    private String systemName;
    private String type;
    private List<String> services;


    public DeviceDto(){}
    public DeviceDto(String type, String systemName, List<String> services) {
        this.type = type;
        this.services = services;
        this.systemName = systemName;
    }
}
