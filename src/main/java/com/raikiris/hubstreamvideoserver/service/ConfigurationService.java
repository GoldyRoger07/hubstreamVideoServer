package com.raikiris.hubstreamvideoserver.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.raikiris.hubstreamvideoserver.model.FileHandler;

@Service
public class ConfigurationService {

    public void startConfig(){
        FileHandler.createFile("C:\\Hubstream","hubstream.config.json");
        createJsonFiles();
        FileHandler.createFolder("C:\\Hubstream\\Films");
        FileHandler.createFolder("C:\\Hubstream\\Series");
        FileHandler.createFolder("C:\\Hubstream\\Animes");
        FileHandler.createFolder("C:\\Hubstream\\Temp");
        FileHandler.createFolder("C:\\Hubstream\\Temp\\Films");
        FileHandler.createFolder("C:\\Hubstream\\Temp\\Series");
        FileHandler.createFolder("C:\\Hubstream\\Temp\\Animes");
        createDefaultConfiguration();
    }

    public void createJsonFiles(){
        File filmsJsonFile = FileHandler.createFile("C:\\Hubstream","films.json");
        
        Map<String,Object> filmJson = new HashMap<>();
        Map<String,Object> serieJson = new HashMap<>();
        Map<String,Object> animeJson = new HashMap<>();
        
        filmJson.put("films",new ArrayList<>());
        serieJson.put("series",new ArrayList<>());
        animeJson.put("animes",new ArrayList<>());


        if(FileHandler.isFileEmpty(filmsJsonFile.getAbsolutePath()))
            FileHandler.writeJsonToFile("C:\\Hubstream","films.json",filmJson);
        
        File seriesJsonFile = FileHandler.createFile("C:\\Hubstream","series.json");
        
        if(FileHandler.isFileEmpty(seriesJsonFile.getAbsolutePath()))
            FileHandler.writeJsonToFile("C:\\Hubstream","series.json",serieJson);

        File animesJsonFile = FileHandler.createFile("C:\\Hubstream","animes.json");
        
        if(FileHandler.isFileEmpty(animesJsonFile.getAbsolutePath()))
            FileHandler.writeJsonToFile("C:\\Hubstream","animes.json",animeJson);
    }

    public void createDefaultConfiguration(){
        String fileName = "C:\\Hubstream\\hubstream.config.json";

        if(FileHandler.isFileEmpty(fileName)){
            Map<String,Object> config = new HashMap<>();
            Map<String,Object> parametresFile = new HashMap<>();
            Map<String,Object> films = new HashMap<>();
            Map<String,Object> series = new HashMap<>();
            Map<String,Object> animes = new HashMap<>();
            Map<String,Object> task = new HashMap<>();
            Map<String,Object> host = new HashMap<>();

            parametresFile.put("isUpdated", false);
            parametresFile.put("folderRacine", "C:/Hubstream");
            parametresFile.put("folderFilms", "C:/Hubstream/Films");
            parametresFile.put("folderSeries", "C:/Hubstream/Series");
            parametresFile.put("folderAnimes", "C:/Hubstream/Animes");

            films.put("isUpdated",false);
            films.put("titres",new ArrayList<>());
            films.put("path","films.json");

            series.put("isUpdated",false);
            series.put("titres",new ArrayList<>());
            series.put("path","series.json");

            animes.put("isUpdated",false);
            animes.put("titres",new ArrayList<>());
            animes.put("path","animes.json");

            task.put("interval",600000);
            task.put("delay",0);

            host.put("hubstreamOnlineApi","http://192.168.0.178:9001/api.online.hubstream.com");
            host.put("hubstreamOfflineApi","http://192.168.0.178:9002/api.offline.hubstream.com");
            host.put("hubstreamVideoServer","http://192.168.0.178:8080");

            config.put("appName","Hubstream");
            config.put("hasUpdate", false);
            config.put("parametresFile",parametresFile);
            config.put("films",films);
            config.put("series",series);
            config.put("animes",animes);
            config.put("task",task);
            config.put("host", host);

            setConfig(config);
        }
    }

    public void setConfig(Map<String,Object> config){
        writeJsonToFile("C:\\Hubstream", "hubstream.config.json", config);
    }

    public void writeJsonToFile(String folderPath,String fileName,Object object){
        FileHandler.writeJsonToFile(folderPath, fileName, object);
    }

    @SuppressWarnings("unchecked")
    public Map<String,Object> getConfig(){
       return (Map<String,Object>) FileHandler.getObjectFromJsonFile("C:\\Hubstream\\hubstream.config.json");
    }


    @SuppressWarnings("unchecked")
    public void renameFolderByContenu(String actualName,String newName,String typeContenu){
        Map<String,Object> config = getConfig();
        Map<String,Object> parametresFileConfig =(HashMap<String,Object>) config.get("parametresFile");
        String chemin = "";

        switch(typeContenu){
            case "film":
                chemin = (String) parametresFileConfig.get("folderFilms");
            break;
            case "serie":
                chemin = (String) parametresFileConfig.get("folderSeries");
            break;
            case "anime":
                chemin = (String) parametresFileConfig.get("folderAnimes");
            break;
        }

        FileHandler.renameFolder(chemin+"/"+actualName, chemin+"/"+newName);

    }



}
