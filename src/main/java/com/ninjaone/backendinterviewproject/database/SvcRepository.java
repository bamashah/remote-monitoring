package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.Svc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvcRepository extends JpaRepository<Svc, String> {

    @Query(value = "SELECT Device_Service.service_Id as service_Id, COUNT(*)*Svc.cost as cost, Svc.display_Value FROM Device_Service, Svc WHERE Device_Service.service_Id = Svc.service_Id  GROUP BY Device_Service.service_Id", nativeQuery = true)
    List<Object[]> countTotalCostByService();
}
