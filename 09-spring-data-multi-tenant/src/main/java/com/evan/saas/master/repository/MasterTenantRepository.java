package com.evan.saas.master.repository;

import com.evan.saas.master.model.MasterTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant,String>{

    @Query("select p from MasterTenant p where p.tenant = :tenant")
    MasterTenant findByTenant(@Param("tenant") String tenant);
}
