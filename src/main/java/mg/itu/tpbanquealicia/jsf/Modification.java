/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquealicia.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import mg.itu.tpbanquealicia.entity.CompteBancaire;
import mg.itu.tpbanquealicia.jsf.util.Util;
import mg.itu.tpbanquealicia.service.GestionnaireCompte;

/**
 *
 * @author alici
 */
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    private Long id;
    private String nom;
    private CompteBancaire compte;

    @Inject
    GestionnaireCompte gestionnaireCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    /**
     * Creates a new instance of Modification
     */
    public Modification() {
    }

    public void loadCompte() {
        if (compte == null) {
            this.compte = gestionnaireCompte.findById(id);
        }
    }
    
    public String modifier(){
        String message = "Nom ("+compte.getNom()+") changé en ("+nom+")";
        compte.setNom(nom);
        gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage(message);
        return "listeComptes?faces-redirect=true";
    }
}
