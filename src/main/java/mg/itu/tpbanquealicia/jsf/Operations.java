/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquealicia.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import mg.itu.tpbanquealicia.entity.CompteBancaire;
import mg.itu.tpbanquealicia.entity.OperationBancaire;
import mg.itu.tpbanquealicia.service.GestionnaireCompte;

/**
 *
 * @author alici
 */
@Named(value = "operations")
@RequestScoped
public class Operations {

    private long idCompte;
    private CompteBancaire compte;
    private List<OperationBancaire> operations;

    @Inject
    GestionnaireCompte gestionnaireCompte;

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public List<OperationBancaire> getOperations() {
        return compte.getOperations();
    }

    public void loadCompte() {
        if (compte == null) {
            this.compte = gestionnaireCompte.findById(idCompte);
        }
    }

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }

    public String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

}
