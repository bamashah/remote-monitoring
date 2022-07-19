package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DeviceTypeService {
    private final DeviceTypeRepository deviceTypeRepository;

    public DeviceTypeService(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    public DeviceType createDeviceType(DeviceType deviceType){
        return deviceTypeRepository.save(deviceType);
    }

    public Optional<DeviceType> getDeviceType(String id){
        return deviceTypeRepository.findById(id);
    }

    public ArrayList<DeviceType> fetchAll(){
        return (ArrayList<DeviceType>) deviceTypeRepository.findAll();
    }

    public void deleteDeviceType(String id) {
        deviceTypeRepository.deleteById(id);
    }
}
