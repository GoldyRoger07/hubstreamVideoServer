package com.raikiris.hubstreamvideoserver.model;

import java.time.LocalDate;


import lombok.Data;

@Data
public class Compte {
   
    private String idCompte;

    private String nom;

    private String prenom;

    private String telephone;

    private LocalDate dateEnregistrement;

    private String username;

    private String password;

    private String role;

    private double solde;

    private String idConnection;

    // @OneToMany(cascade = CascadeType.ALL, targetEntity = ActiverPlan.class,
    // mappedBy = "compte")
    // private List<ActiverPlan> activerPlans;

    public boolean testRetrait(double montantARetirer) {
        return montantARetirer <= solde && montantARetirer > 0;
    }

    public void effectuerRetrait(double montantARetirer) {
        solde -= montantARetirer;
    }

}
