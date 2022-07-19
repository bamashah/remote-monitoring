package com.ninjaone.backendinterviewproject.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Device {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long deviceId;

    @Column(unique=true)
    private String systemName;

    @OneToOne
    @JoinColumn(referencedColumnName = "deviceTypeId")
    private DeviceType type;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="Device_Service",
            joinColumns = @JoinColumn(name = "deviceId"),
            inverseJoinColumns = @JoinColumn(name = "serviceId"))
    private Set<Svc> services;


    public Device(){}
    public Device(Long deviceId, String systemName, Set<Svc> services) {
        this.deviceId = deviceId;
        this.systemName = systemName;
        this.services = services;
    }

    public Device(DeviceType type, String systemName, Set<Svc> services) {
        this.type = type;
        this.services = services;
        this.systemName = systemName;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public String getSystemName() {
        return systemName;
    }

    public DeviceType getType() {
        return type;
    }

    public Set<Svc> getServices() {
        return services;
    }

    public Long getTotalMonthlyCost() {

        return services.stream()
                .map(service -> service.getCost())
                .reduce(0L, (a, b) -> a + b);
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public void setServices(Set<Svc> services) {
        this.services = services;
    }
}
