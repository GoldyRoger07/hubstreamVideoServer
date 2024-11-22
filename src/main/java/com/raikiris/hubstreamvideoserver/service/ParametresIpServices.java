package com.raikiris.hubstreamvideoserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raikiris.hubstreamvideoserver.model.ParametresIp;
import com.raikiris.hubstreamvideoserver.repository.ParametresIpRepository;

@Service
public class ParametresIpServices {
    
    @Autowired
    ParametresIpRepository parametresIpRepository;

    public List<ParametresIp> getParamIps(){
        return parametresIpRepository.findAll();
    }

    public Optional<ParametresIp> getParamIp(int id){
        return parametresIpRepository.findById(id);
    }

    public ParametresIp save(ParametresIp parametresIp){
        return parametresIpRepository.save(parametresIp);
    }

    public void delete(int id){
        parametresIpRepository.deleteById(id);
    }

    public void deleteAll(){
        parametresIpRepository.deleteAll();
    }

    
}
