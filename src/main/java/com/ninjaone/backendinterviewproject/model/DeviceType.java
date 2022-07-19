package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeviceType {
    @Id
    private String deviceTypeId;
    private String displayValue;

    public DeviceType(){}
    public DeviceType(String deviceTypeId, String displayValue) {
        this.deviceTypeId = deviceTypeId;
        this.displayValue = displayValue;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
