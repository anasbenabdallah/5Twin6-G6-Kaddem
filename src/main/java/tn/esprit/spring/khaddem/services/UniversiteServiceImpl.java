package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UniversiteServiceImpl implements  IUniversiteService{
    private final UniversiteRepository universiteRepository;
    private final DepartementRepository departementRepository;

    @Autowired
    public UniversiteServiceImpl(UniversiteRepository universiteRepository, DepartementRepository departementRepository) {
        this.universiteRepository = universiteRepository;
        this.departementRepository = departementRepository;
    }
    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        log.debug("u :"+u.getNomUniv());
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite updateUniversite(Universite u) {
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite retrieveUniversite(Integer idUniversite) {
        Optional<Universite> universiteOptional = universiteRepository.findById(idUniversite);

        if (universiteOptional.isPresent()) {
            return universiteOptional.get();
        } else {
            return null;
        }
    }


    @Transactional
    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) {
        Universite universite = universiteRepository.findById(universiteId)
                .orElseThrow(() -> new NoSuchElementException("Universite not found with ID: " + universiteId));
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new NoSuchElementException("Departement not found with ID: " + departementId));

        universite.getDepartements().add(departement);
        log.info("departements number " + universite.getDepartements().size());
    }



}
