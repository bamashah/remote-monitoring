package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.SvcRepository;
import com.ninjaone.backendinterviewproject.dto.SvcCostDto;
import com.ninjaone.backendinterviewproject.model.Svc;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SvcService {
    private final SvcRepository serviceRepository;

    public SvcService(SvcRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Svc createSvc(Svc service) throws Exception {
        Optional<Svc> svcOpt = getSvc(service.getServiceId());

        if (svcOpt.isPresent()) {
            throw new Exception("Service " + service.getServiceId() + " already exists.");
        }

        return serviceRepository.save(service);
    }

    public Optional<Svc> getSvc(String id){
        return serviceRepository.findById(id);
    }

    public ArrayList<Svc> fetchAll(){
        return (ArrayList<Svc>) serviceRepository.findAll();
    }

    public void deleteSvc(String id) {
        serviceRepository.deleteById(id);
    }

    public List<SvcCostDto> getCostsGroupedByServiceType() {
        List<Object[]> objList = serviceRepository.countTotalCostByService();
        List<SvcCostDto> list = objList.stream()
                .map(arr -> new SvcCostDto(arr[0].toString(), ((BigInteger)arr[1]).longValue()))
                .collect(Collectors.toList());

        return list;
    }
}
