package com.raikiris.hubstreamvideoserver.model;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class ActiverPlan {


    private int idActiverPlan;
    
    private LocalDateTime dateDebut;
    
    private LocalDateTime dateExpiration;

    private String etat;

    private Compte compte;

    private Plan plan;

}

