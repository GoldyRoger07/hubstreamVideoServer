package com.raikiris.hubstreamvideoserver.model;

import lombok.Data;

@Data
public class Plan {

    private int idPlan;

    private String titre;

    private int duree;

    private String dureeExtension;

    private double prix;

    private String type;

}