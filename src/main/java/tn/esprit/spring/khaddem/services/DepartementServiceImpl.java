package tn.esprit.spring.khaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements IDepartementService {
    private final DepartementRepository departementRepository;
    private final UniversiteRepository universiteRepository;

    @Autowired
    public DepartementServiceImpl(DepartementRepository departementRepository, UniversiteRepository universiteRepository) {
        this.departementRepository = departementRepository;
        this.universiteRepository = universiteRepository;
    }

    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        departementRepository.save(d);
        return d;
    }

    @Override
    public Departement updateDepartement(Departement d) {
        Optional<Departement> existingDepartement = departementRepository.findById(d.getIdDepartement());

        if (existingDepartement.isPresent()) {
            Departement updatedDepartement = existingDepartement.get();
            updatedDepartement.setNomDepart(d.getNomDepart());

            departementRepository.save(updatedDepartement);
            return updatedDepartement;
        } else {
            return null;
        }
    }


    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        Optional<Departement> departementOptional = departementRepository.findById(idDepart);

        if (departementOptional.isPresent()) {
            return departementOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Optional<Universite> universiteOptional = universiteRepository.findById(idUniversite);

        if (universiteOptional.isPresent()) {
            Universite universite = universiteOptional.get();
            return universite.getDepartements();
        } else {
            return Collections.emptyList();
        }
    }
}
