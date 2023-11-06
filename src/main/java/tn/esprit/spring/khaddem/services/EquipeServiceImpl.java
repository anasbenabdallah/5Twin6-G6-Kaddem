package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EquipeServiceImpl implements IEquipeService {

    EquipeRepository equipeRepository;

    ContratRepository contratRepository;

    @Override
    public List<Equipe> retrieveAllEquipes() {
        return equipeRepository.findAll();
    }

    @Transactional
    public Equipe addEquipe(Equipe e) {

        equipeRepository.save(e);
        return e;
    }

    @Override
    public Equipe updateEquipe(Equipe e) {
        Optional<Equipe> existingEquipe = equipeRepository.findById(e.getIdEquipe());

        if (existingEquipe.isPresent()) {
            Equipe updatedEquipe = existingEquipe.get();
            updatedEquipe.setNomEquipe(e.getNomEquipe());
            updatedEquipe.setNiveau(e.getNiveau());
            equipeRepository.save(updatedEquipe);
            return updatedEquipe;
        } else {
            Equipe emptyEquipe = new Equipe();
            emptyEquipe.setNomEquipe("Default Name");
            return emptyEquipe;
        }
    }


    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);

        if (equipeOptional.isPresent()) {
            return equipeOptional.get();
        } else {
            return null;
        }
    }


    public void evoluerEquipes() {
        log.info("debut methode evoluerEquipes");

        List<Equipe> equipes = equipeRepository.findAll();
        log.debug("nombre equipes: " + equipes.size());

        for (Equipe equipe : equipes) {
            if (shouldUpdateEquipe(equipe)) {
                updateEquipeNiveau(equipe);
            }
        }

        log.info("fin methode evoluerEquipes");
    }

    private boolean shouldUpdateEquipe(Equipe equipe) {
        return equipe.getEtudiants() != null && !equipe.getEtudiants().isEmpty() &&
                (equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR));
    }

    private void updateEquipeNiveau(Equipe equipe) {
        List<Etudiant> etudiants = equipe.getEtudiants();
        int nbEtudiantsAvecContratsActifs = 0;

        for (Etudiant etudiant : etudiants) {
            nbEtudiantsAvecContratsActifs += countActiveContrats(etudiant);
            if (nbEtudiantsAvecContratsActifs >= 3) {
                break;
            }
        }

        log.info("nbEtudiantsAvecContratsActifs: " + nbEtudiantsAvecContratsActifs);

        if (nbEtudiantsAvecContratsActifs >= 3) {
            updateEquipeNiveauBasedOnCurrentLevel(equipe);
        }
    }

    private int countActiveContrats(Etudiant etudiant) {
        List<Contrat> contrats = contratRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant());
        int activeContrats = 0;

        for (Contrat contrat : contrats) {
            long differenceInTime = contrat.getDateFinContrat().getTime() - contrat.getDateDebutContrat().getTime();
            long differenceInYears = (differenceInTime / (1000L * 60 * 60 * 24 * 365));

            if ((contrat.getArchived() == null || !contrat.getArchived()) && differenceInYears > 1) {
                activeContrats++;
            }
        }

        return activeContrats;
    }

    private void updateEquipeNiveauBasedOnCurrentLevel(Equipe equipe) {
        if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
            log.info("mise à jour du niveau de l'équipe " + equipe.getNomEquipe() +
                    " du niveau " + equipe.getNiveau() + " vers le niveau supérieur SENIOR");
            equipe.setNiveau(Niveau.SENIOR);
            equipeRepository.save(equipe);
        } else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
            log.info("mise à jour du niveau de l'équipe " + equipe.getNomEquipe() +
                    " du niveau " + equipe.getNiveau() + " vers le niveau supérieur EXPERT");
            equipe.setNiveau(Niveau.EXPERT);
            equipeRepository.save(equipe);
        }
    }


}
