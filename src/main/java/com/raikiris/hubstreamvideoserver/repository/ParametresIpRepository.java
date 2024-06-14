package com.raikiris.hubstreamvideoserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raikiris.hubstreamvideoserver.model.ParametresIp;

@Repository
public interface ParametresIpRepository extends JpaRepository<ParametresIp,Integer>{
    
}
