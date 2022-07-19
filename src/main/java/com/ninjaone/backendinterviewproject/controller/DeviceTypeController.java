package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.DeviceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/device-type")
public class DeviceTypeController {
    private final DeviceTypeService deviceTypeService;

    public DeviceTypeController(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> createService(@RequestBody DeviceType service){
        try {
            DeviceType saved =  deviceTypeService.createDeviceType(service);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<DeviceType> getServiceById(@PathVariable String id){
        Optional<DeviceType> deviceTypeOpt = deviceTypeService.getDeviceType(id);
        if (deviceTypeOpt.isPresent()) {
            return new ResponseEntity<>(deviceTypeOpt.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ArrayList<DeviceType> fetchAll() {
        return deviceTypeService.fetchAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteService(@PathVariable String id){
        deviceTypeService.deleteDeviceType(id);
    }

}
