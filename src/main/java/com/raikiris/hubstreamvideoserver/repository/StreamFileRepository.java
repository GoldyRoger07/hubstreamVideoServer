package com.raikiris.hubstreamvideoserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raikiris.hubstreamvideoserver.model.StreamFile;

public interface StreamFileRepository extends JpaRepository<StreamFile,Integer>{
    
    public Optional<StreamFile> findByName(String fileName);

    public Optional<StreamFile> findByFilePath(String filePath);
}

