package tn.esprit.spring.khaddem.dto;

import tn.esprit.spring.khaddem.entities.Specialite;

import java.util.Date;

public class ContratDTO {
    private Integer idContrat;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;
    private Boolean archived;
    private Integer montantContrat;

    public Integer getIdContrat() {
        return idContrat;
    }

    public Date getDateDebutContrat() {
        return dateDebutContrat;
    }

    public Date getDateFinContrat() {
        return dateFinContrat;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public Boolean getArchived() {
        return archived;
    }

    public Integer getMontantContrat() {
        return montantContrat;
    }

    // Add getters and setters for the above fields

    // Create a constructor to map from Contrat entity
    public ContratDTO(Integer idContrat, Date dateDebutContrat, Date dateFinContrat, Specialite specialite, Boolean archived, Integer montantContrat) {
        this.idContrat = idContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archived = archived;
        this.montantContrat = montantContrat;
    }
}
