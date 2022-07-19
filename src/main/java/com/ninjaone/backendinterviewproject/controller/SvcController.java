package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.SvcCostDto;
import com.ninjaone.backendinterviewproject.model.Svc;
import com.ninjaone.backendinterviewproject.service.SvcService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class SvcController {
    private final SvcService serviceService;

    public SvcController(SvcService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> createService(@RequestBody Svc service){
        try {
            Svc saved =  serviceService.createSvc(service);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<Svc> getServiceById(@PathVariable String id){
        Optional<Svc> serviceOpt = serviceService.getSvc(id);
        if (serviceOpt.isPresent()) {
            return new ResponseEntity<>(serviceOpt.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ArrayList<Svc> fetchAll() {
        return serviceService.fetchAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteService(@PathVariable String id){
        serviceService.deleteSvc(id);
    }

    @GetMapping("/total-costs")
    private List<SvcCostDto> getCosts(){
        return serviceService.getCostsGroupedByServiceType();
    }
}
