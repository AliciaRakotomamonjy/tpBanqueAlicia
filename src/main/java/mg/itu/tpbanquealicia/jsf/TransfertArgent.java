/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquealicia.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanquealicia.entity.CompteBancaire;
import mg.itu.tpbanquealicia.jsf.util.Util;
import mg.itu.tpbanquealicia.service.GestionnaireCompte;

/**
 *
 * @author alici
 */
@Named(value = "transfertArgent")
@RequestScoped
public class TransfertArgent {

    private long idSource;
    private long idDestination;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public long getIdSource() {
        return idSource;
    }

    public void setIdSource(long idSource) {
        this.idSource = idSource;
    }

    public long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(long idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

    public String transferer() {
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet idSource !", "Aucun compte avec cet idSource !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Le solde du compte source n'est pas suffisant !", "Le solde du compte source n'est pas suffisant !", "form:montant");
                erreur = true;
            }
        }

        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        if (destination == null) {
            // Message d'erreur associé au composant destination ; form:destination est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la destination est "destination"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet idDestination!", "Aucun compte avec cet idDestination !", "form:destination");
            erreur = true;
        }

        if (montant <= 0) {
            // Message d'erreur associé au composant montant ; form:montant est le montant
            // si l'id du formulaire est "form" et l'id du champ de saisie de montant est "montant"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Veuillez insérez un montant positif !", "Veuillez insérez un montant positif !", "form:montant");
            erreur = true;
        }

        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }

        gestionnaireCompte.transferer(source, destination, montant);
         Util.addFlashInfoMessage("Transfert de "+montant+" Ar depuis "+source.getNom()+" vers "+destination.getNom()+" correctement effectué!");
        return "listeComptes?faces-redirect=true";
    }

}
