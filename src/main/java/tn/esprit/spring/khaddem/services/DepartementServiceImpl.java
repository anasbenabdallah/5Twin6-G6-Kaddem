package tn.esprit.spring.khaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DepartementServiceImpl implements IDepartementService {

    private final DepartementRepository departementRepository;
    private final UniversiteRepository universiteRepository;

    @Autowired
    public DepartementServiceImpl(
            DepartementRepository departementRepository,
            UniversiteRepository universiteRepository) {
        this.departementRepository = departementRepository;
        this.universiteRepository = universiteRepository;
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
    public Departement updateDepartement(Departement d) {
        saveDepartement(d);
        return d;
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