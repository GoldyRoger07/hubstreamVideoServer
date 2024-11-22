package com.raikiris.hubstreamvideoserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raikiris.hubstreamvideoserver.model.ParametresFile;

@Repository
public interface ParametresFileRepository extends JpaRepository<ParametresFile,Integer>{
    
}
