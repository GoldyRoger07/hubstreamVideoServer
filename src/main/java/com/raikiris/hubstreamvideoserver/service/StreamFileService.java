package com.raikiris.hubstreamvideoserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raikiris.hubstreamvideoserver.model.StreamFile;
import com.raikiris.hubstreamvideoserver.repository.StreamFileRepository;

@Service
public class StreamFileService {
    @Autowired
    StreamFileRepository streamFileRepository;

    public Optional<StreamFile> getStreamFile(int id){
        return streamFileRepository.findById(id);
    }

    public Optional<StreamFile> getStreamFile(String fileName){
        return streamFileRepository.findByName(fileName);
    }


}