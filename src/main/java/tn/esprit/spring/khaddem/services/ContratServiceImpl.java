package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ContratServiceImpl implements  IContratService{



    ContratRepository contratRepository;

    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        log.info("debut methode retrieveAllContrats");
        return contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(Contrat ce) {
        log.info("debut methode updateContrat");
        contratRepository.save(ce);
        return ce;
    }

    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        log.info("debut methode retrieveContrat");
        return contratRepository.findById(idContrat).get();
    }

    @Override
    public void removeContrat(Integer idContrat) {
        log.info("debut methode removeContrat");
        contratRepository.deleteById(idContrat);
    }

    @Override
    public Contrat addContrat(Contrat contrat) {
        contratRepository.save(contrat);
        return contrat;
    }

    @Transactional
    public Contrat addAndAffectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        Long startDate = new Date().getTime();
        log.info("startDate: "+startDate);
        log.info("debut methode addAndAffectContratToEtudiant");
        Etudiant etudiant=etudiantRepository.findByNomEAndPrenomE(nomE,prenomE);
        log.info("etudiant: "+etudiant.getNomE()+" "+etudiant.getPrenomE());
        // nb contrats actifs
        Integer nbContratsActifs= etudiant.getContrats().size();
        if(nbContratsActifs>5) {
            log.info("nombre de contrats autorisés est atteint");
            Long endDate = new Date().getTime();
            Long executionTime = endDate-startDate;
            log.info("endDate: "+startDate);
            log.info("executionTime: "+executionTime+ " ms");
            return ce;
        }
        log.info("nb Contrats en cours: "+nbContratsActifs);
        contratRepository.save(ce);
        ce.setEtudiant(etudiant);
        log.info("fin methode addAndAffectContratToEtudiant");
        Long endDate = new Date().getTime();
        Long executionTime = endDate-startDate;

        log.info("endDate: "+startDate);
        log.info("executionTime: "+executionTime+ " ms");

        return ce;
    }

    public 	Integer nbContratsValides(Date startDate, Date endDate){
        return contratRepository.getnbContratsValides(startDate, endDate);
    }
    public void retrieveAndUpdateStatusContrat() {
        log.info("debut methode retrieveAndUpdateStatusContrat");
        List<Contrat> contrats = contratRepository.findAll();
        log.info("total contrats :" + contrats.size());

        for (Contrat contrat : contrats) {
            log.info("id: " + contrat.getIdContrat());
            log.info("date fin" + contrat.getDateFinContrat());
            log.info("archived " + contrat.getArchived());

            Date dateSysteme = new Date();

            if (contrat.getArchived() == null || !contrat.getArchived()) {
                long differenceInTime = contrat.getDateFinContrat().getTime() - dateSysteme.getTime();
                long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
                log.info("difference in days : " + differenceInDays);
                if (differenceInDays == 15) {
                    log.info("Contrat Commencant le : " + contrat.getDateDebutContrat() + "pour l'etudiant " + contrat.getEtudiant().getNomE() +
                            " " + contrat.getEtudiant().getPrenomE() + " va bientot s achever le "
                            + contrat.getDateFinContrat());
                }
                if (differenceInDays == 0) {
                    log.info("jour j: " + contrat.getIdContrat());
                    contrat.setArchived(true);
                    contratRepository.save(contrat);
                }
            }
        }
    }


    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate) {
        float differenceInTime = (endDate.getTime() - startDate.getTime());
        float differenceInDays = (differenceInTime / (1000f * 60 * 60 * 24)) % 365;
        float differenceInMonths = differenceInDays / 30;
        List<Contrat> contrats = contratRepository.findAll();
        float totalChiffreAffaire = 0;
        float chiffreAffaireIA = 0;
        float chiffreAffaireCloud = 0;
        float chiffreAffaireReseau = 0;
        float chiffreAffaireSecurite = 0;

        for (Contrat contrat : contrats) {
            if (contrat.getSpecialite() == Specialite.IA) {
                totalChiffreAffaire += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireIA += (differenceInMonths * contrat.getMontantContrat());
            } else if (contrat.getSpecialite() == Specialite.CLOUD) {
                totalChiffreAffaire += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireCloud += (differenceInMonths * contrat.getMontantContrat());
            } else if (contrat.getSpecialite() == Specialite.RESEAU) {
                totalChiffreAffaire += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireReseau += (differenceInMonths * contrat.getMontantContrat());
            } else if (contrat.getSpecialite() == Specialite.SECURITE) {
                totalChiffreAffaire += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireSecurite += (differenceInMonths * contrat.getMontantContrat());
            }
        }

        log.info("Total Chiffre d'Affaire Entre Deux Dates: {}", totalChiffreAffaire);
        log.info("Chiffre d'Affaire IA: {}", chiffreAffaireIA);
        log.info("Chiffre d'Affaire Cloud: {}", chiffreAffaireCloud);
        log.info("Chiffre d'Affaire Reseau: {}", chiffreAffaireReseau);
        log.info("Chiffre d'Affaire Securite: {}", chiffreAffaireSecurite);

        return totalChiffreAffaire;
    }
}
