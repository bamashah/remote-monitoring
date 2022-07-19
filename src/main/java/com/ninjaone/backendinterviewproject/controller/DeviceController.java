package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    private ResponseEntity<?> createDevice(@RequestBody DeviceDto deviceDto) throws Exception {
        try {
            Device saved =  deviceService.createDevice(deviceDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable(value = "id") long id) {
        Optional<Device> deviceOpt = deviceService.getDevice(id);
        if (deviceOpt.isPresent()) {
            return new ResponseEntity<>(deviceOpt.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ArrayList<Device> fetchAll() {
        return deviceService.fetchAll();
    }


    @PutMapping("/{id}")
    private ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto) throws Exception {
        try {
            Device saved =  deviceService.updateDevice(id, deviceDto);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteDevice(@PathVariable Long id){
        deviceService.deleteDevice(id);
    }

    @GetMapping("/total-cost")
    private long getMonthlyCost(){
        return deviceService.calculateMonthlyCost();
    }



}
