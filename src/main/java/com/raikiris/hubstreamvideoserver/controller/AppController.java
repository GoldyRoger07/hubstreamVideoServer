package com.raikiris.hubstreamvideoserver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.raikiris.hubstreamvideoserver.model.StreamFile;
import com.raikiris.hubstreamvideoserver.model.TestResponse;
import com.raikiris.hubstreamvideoserver.service.ConfigurationService;
// import com.raikiris.hubstreamvideoserver.service.ActiverPlanService;
import com.raikiris.hubstreamvideoserver.service.ParametresFileService;
import com.raikiris.hubstreamvideoserver.service.ParametresIpServices;
import com.raikiris.hubstreamvideoserver.service.StreamFileService;

// import com.raikiris.hubstreamvideoserver.service.CompteService;

import reactor.core.publisher.Mono;

@RestController
public class AppController {

  @Autowired
  ParametresFileService parametresFileService;

  @Autowired
  StreamFileService streamFileService;

  @Autowired
  ParametresIpServices parametresIpServices;

  @Autowired
  RestTemplate restTemplate;

  String baseUrlApiOnline = "http://192.168.0.178:9001/api.online.hubstream.com";

  String cheminSeries = "";
  String cheminAnimes = "";
  String cheminFilms = "";

  @Autowired
  ConfigurationService configurationService;

  @SuppressWarnings("unchecked")
  public void initConfig(){
    Map<String,Object> config = configurationService.getConfig();
    Map<String,Object> host =(HashMap<String,Object>) config.get("host");
    Map<String,Object> parametresFile = (HashMap<String,Object>)config.get("parametresFile");
    
    cheminAnimes = (String) parametresFile.get("folderAnimes");

    cheminSeries = (String) parametresFile.get("folderSeries");

    cheminFilms = (String) parametresFile.get("folderFilms");
    
    baseUrlApiOnline = (String) host.get("hubstreamOnlineApi");

  }

  @GetMapping(value = "vid/film/{videoName:.+}/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Mono<Resource> getFilm(@PathVariable("videoName") String videoName, @PathVariable("id") String idCompte) {
    
    initConfig();
    
    StreamFile streamFile = streamFileService.getStreamFile(videoName).get();
    String url = baseUrlApiOnline+"/activerPlans/test/compte/"+idCompte+"/Film";
    ResponseEntity<TestResponse> responseEntity = restTemplate.getForEntity(url, TestResponse.class);
    TestResponse testResponse = responseEntity.getBody();
    if (testResponse!=null && testResponse.isPass())
       return Mono.fromSupplier(() -> new FileSystemResource(cheminFilms+ "/" + streamFile.getFilePath()));
    

    return null;
  }

  @GetMapping(value = "vid/serie/{videoName:.+}/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Mono<Resource> getSerie(@PathVariable("videoName") String videoName, @PathVariable("id") String idCompte) {
    initConfig();
    StreamFile streamFile = streamFileService.getStreamFile(videoName).get();
    String url = baseUrlApiOnline+"/activerPlans/test/compte/"+idCompte+"/Serie";
    ResponseEntity<TestResponse> responseEntity = restTemplate.getForEntity(url, TestResponse.class);
    TestResponse testResponse = responseEntity.getBody();
    if (testResponse!=null && testResponse.isPass())
      return Mono.fromSupplier(() -> new FileSystemResource(cheminSeries+"/" + streamFile.getFilePath()));
   
    return null;
  }

  @GetMapping(value = "vid/anime/{videoName:.+}/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public Mono<Resource> getAnime(@PathVariable("videoName") String videoName, @PathVariable("id") String idCompte) {
    initConfig();
    StreamFile streamFile = streamFileService.getStreamFile(videoName).get();
    String url = baseUrlApiOnline+"/activerPlans/test/compte/"+idCompte+"/Anime";
    ResponseEntity<TestResponse> responseEntity = restTemplate.getForEntity(url, TestResponse.class);
    TestResponse testResponse = responseEntity.getBody();
    if (testResponse!=null && testResponse.isPass())
        return Mono.fromSupplier(() -> new FileSystemResource(cheminAnimes+ "/" + streamFile.getFilePath()));
    
    return null;
  }

}
