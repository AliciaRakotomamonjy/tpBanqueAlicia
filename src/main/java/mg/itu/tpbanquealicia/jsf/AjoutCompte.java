/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquealicia.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.tpbanquealicia.entity.CompteBancaire;
import mg.itu.tpbanquealicia.jsf.util.Util;
import mg.itu.tpbanquealicia.service.GestionnaireCompte;

/**
 *
 * @author alici
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte implements Serializable {

    private String nom;
    @PositiveOrZero(message = "Le solde ne peut pas être négatif.")
    private int solde;

    @Inject
    GestionnaireCompte gestionnaireCompte;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String action() {
        CompteBancaire compteBancaire = new CompteBancaire(nom, solde);
        gestionnaireCompte.creerCompte(compteBancaire);
        Util.addFlashInfoMessage("Ajout de compte correctement effectué!");
        return "listeComptes?faces-redirect=true";
    }

}
