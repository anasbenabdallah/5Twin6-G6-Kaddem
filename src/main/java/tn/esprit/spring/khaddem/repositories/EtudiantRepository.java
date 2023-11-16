package tn.esprit.spring.khaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.khaddem.entities.Etudiant;


@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {



















    Etudiant findByNomEAndPrenomE(String nomE, String prenomE);











}