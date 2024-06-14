package com.raikiris.hubstreamvideoserver.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raikiris.hubstreamvideoserver.model.ParametresFile;
import com.raikiris.hubstreamvideoserver.repository.ParametresFileRepository;

@Service
public class ParametresFileService {

    @Autowired
    ParametresFileRepository parametresFileRepository;

    public Optional<ParametresFile> getParametresFile(int id) {
        return parametresFileRepository.findById(id);
    }

    public List<ParametresFile> getParametresFiles() {
        return parametresFileRepository.findAll();
    }

    public ParametresFile save(ParametresFile parametresFile) {
        return parametresFileRepository.save(parametresFile);
    }

}
