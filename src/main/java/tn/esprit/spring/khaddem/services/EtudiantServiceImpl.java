package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{

    EtudiantRepository etudiantRepository;




    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);
        if (optionalEtudiant.isPresent()) {
            return optionalEtudiant.get();
        } else {
            // Handle the case when the optional is empty, e.g., return null or throw an exception.
            // You might want to consider throwing EntityNotFoundException or a custom exception.
            return null;
        }
    }


    @Override
    public void removeEtudiant(Integer idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }



}