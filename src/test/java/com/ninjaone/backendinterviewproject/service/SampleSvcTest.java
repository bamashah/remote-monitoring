package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.SvcRepository;
import com.ninjaone.backendinterviewproject.model.Svc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SampleSvcTest {
    public static final String ID = "BACKUP";

    @Mock
    private SvcRepository serviceRepository;

    @InjectMocks
    private SvcService svcService;

    private Svc serviceEntity;

    @BeforeEach
    void setup(){
        serviceEntity = new Svc(ID, 4L, "Back up");
    }

    @Test
    void getService() {
        when(serviceRepository.findById(ID)).thenReturn(Optional.of(serviceEntity));
        Optional<Svc> serviceOptional = svcService.getSvc(ID);
        Svc actualEntity = serviceOptional.orElse(null);
        assert actualEntity != null;
        assertEquals(serviceEntity.getCost(), actualEntity.getCost());
    }

    @Test
    void createService() throws Exception {
        when(serviceRepository.save(serviceEntity)).thenReturn(serviceEntity);
        assertEquals(serviceEntity, svcService.createSvc(serviceEntity));
    }

    @Test
    void deleteService(){
        doNothing().when(serviceRepository).deleteById(ID);
        svcService.deleteSvc(ID);
        Mockito.verify(serviceRepository, times(1)).deleteById(ID);
    }
}
