package tn.esprit.spring.khaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DepartementServiceImpl implements IDepartementService {

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementServiceImpl(
            DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }
    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        saveDepartement(d);
        return d;
    }

    @Override
    public Departement updateDepartement(Departement updatedDepartement) {
        Optional<Departement> existingDepartementOptional = departementRepository.findById(updatedDepartement.getIdDepartement());

        if (existingDepartementOptional.isPresent()) {
            Departement existingDepartement = existingDepartementOptional.get();
            existingDepartement.setNomDepart(updatedDepartement.getNomDepart());

            saveDepartement(existingDepartement);

            return existingDepartement;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with ID: " + updatedDepartement.getIdDepartement());
        }
    }

    private void saveDepartement(Departement d) {
        departementRepository.save(d);
    }

    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);

        return optionalDepartement.orElse(null);
    }




}