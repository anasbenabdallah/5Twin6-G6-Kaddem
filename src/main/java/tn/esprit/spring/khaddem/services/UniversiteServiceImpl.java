package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;

    @Autowired
    public UniversiteServiceImpl(UniversiteRepository universiteRepository) {
        this.universiteRepository = universiteRepository;
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
            // Handle the case when the value is not present, for example, throw an exception or return a default value.
            // You can throw an exception like NoSuchElementException, or return a default Universite instance.
            throw new NoSuchElementException("Universite not found for id: " + idUniversite);
            // Or return a default value
            // return new Universite(); // Assuming Universite has a no-argument constructor.
        }
    }



}