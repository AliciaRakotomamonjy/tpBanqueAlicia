/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquealicia.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanquealicia.entity.CompteBancaire;
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
    
    public String transferer(){
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        gestionnaireCompte.transferer(source, destination, montant);
        return "listeComptes?faces-redirect=true";
    }
    
}
