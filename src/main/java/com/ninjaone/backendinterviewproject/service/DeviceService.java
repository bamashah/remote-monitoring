package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.model.Svc;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final SvcService serviceService;
    private final DeviceTypeService deviceTypeService;

    public DeviceService(DeviceRepository deviceRepository, SvcService serviceService, DeviceTypeService deviceTypeService) {
        this.deviceRepository = deviceRepository;
        this.serviceService = serviceService;
        this.deviceTypeService = deviceTypeService;
    }

    public Device createDevice(DeviceDto deviceDto) throws Exception {

        // make sure system name is not duplicate
        validateDeviceName(deviceDto);

        // is device type valid
        DeviceType deviceType = validateDeviceType(deviceDto);

        // are the services being added valid
        Set<Svc> servicesToAdd = validateServices(deviceDto);

        Device device = new Device(deviceType, deviceDto.getSystemName(), servicesToAdd);

        return deviceRepository.save(device);
    }


    public Optional<Device> getDevice(long id){
        return deviceRepository.findById(id);
    }

    public ArrayList<Device> fetchAll(){
        return (ArrayList<Device>) deviceRepository.findAll();
    }

    public Device updateDevice(long id, DeviceDto deviceDto) throws Exception {

        Optional<Device> deviceOpt = getDevice(id);
        if (!deviceOpt.isPresent()) throw new Exception("Could not find the device with id " + id);
        Device device = deviceOpt.get();

        if (!device.getSystemName().equals(deviceDto.getSystemName())) {
            // make sure system name is not duplicate
            validateDeviceName(deviceDto);
            device.setSystemName(deviceDto.getSystemName());
        }

        // is device type valid
        DeviceType deviceType = validateDeviceType(deviceDto);

        // are the services being added valid
        Set<Svc> servicesToAdd = validateServices(deviceDto);

        device.setType(deviceType);
        device.setServices(servicesToAdd);
        return deviceRepository.save(device);
    }


    public void deleteDevice(long id) {
        deviceRepository.deleteById(id);
    }

    public long calculateMonthlyCost () {
        ArrayList<Device> devices = (ArrayList<Device>) deviceRepository.findAll();

        return devices.stream()
                .map(device -> device.getTotalMonthlyCost())
                .reduce(0L, (a, b) -> a + b);
    }

    private void validateDeviceName (DeviceDto deviceDto) throws Exception {
        Optional<Device> deviceOpt = deviceRepository.findBySystemName(deviceDto.getSystemName());
        if (deviceOpt.isPresent()) {
            throw new Exception("Device " + deviceDto.getSystemName() + " already exists.");
        }
    }

    private DeviceType validateDeviceType (DeviceDto deviceDto) throws Exception {
        Optional<DeviceType> deviceTypeOpt = deviceTypeService.getDeviceType(deviceDto.getType());
        if (!deviceTypeOpt.isPresent()) {
            throw new Exception("Device type " + deviceDto.getType() + " does not exist.");
        }

        return deviceTypeOpt.get();
    }

    private Set<Svc> validateServices (DeviceDto deviceDto) throws Exception {
        Set<Svc> servicesToAdd = new HashSet<>();

        for (String s : deviceDto.getServices()) {
            Optional<Svc> svcOptional = serviceService.getSvc(s);
            if (svcOptional.isPresent()) {
                servicesToAdd.add(svcOptional.get());
            } else {
                throw new Exception("Could not find the service " + s);
            }
        }
        servicesToAdd.add(serviceService.getSvc("DEVICE").get());

        return servicesToAdd;
    }

}
