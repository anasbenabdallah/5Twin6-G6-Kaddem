package tn.esprit.spring.khaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartement;
    private String nomDepart;
    /*@OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Etudiant> etudiants;*/
    public Departement(Integer idDepartement, String nomDepart) {
        super();
        this.idDepartement = idDepartement;
        this.nomDepart = nomDepart;
    }

    @ManyToOne
    @JoinColumn(name = "universite_id")
    @JsonIgnore
    private Universite universite;
}