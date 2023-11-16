package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ContratServiceImpl implements  IContratService{



    ContratRepository contratRepository;

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
    public Contrat addContrat(Contrat c) {
        // start date t1
        contratRepository.save(c);

        return c;
    }

    public 	Integer nbContratsValides(Date startDate, Date endDate){
        return contratRepository.getnbContratsValides(startDate, endDate);
    }


    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate){
        float differenceInTime = (endDate.getTime() - startDate.getTime());
        float differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
        float differenceInmonths =differenceInDays/30;
        List<Contrat> contrats=contratRepository.findAll();
        float chiffreAffaireEntreDeuxDates=0;
        float chiffreAffaireEntreDeuxDatesIA=0;
        float chiffreAffaireEntreDeuxDatesCloud=0;
        float chiffreAffaireEntreDeuxDatesReseau=0;
        float chiffreAffaireEntreDeuxDatesSecurite=0;

        for (Contrat contrat : contrats) {
            if (contrat.getSpecialite()== Specialite.IA){
                chiffreAffaireEntreDeuxDates+=(differenceInmonths*contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesIA+=(differenceInmonths*contrat.getMontantContrat());

            } else if (contrat.getSpecialite()== Specialite.CLOUD) {
                chiffreAffaireEntreDeuxDates+=(differenceInmonths*contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesCloud+=(differenceInmonths*contrat.getMontantContrat());
            }
            else if (contrat.getSpecialite()== Specialite.RESEAU) {
                chiffreAffaireEntreDeuxDates+=(differenceInmonths*contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesReseau+=(differenceInmonths*contrat.getMontantContrat());

            }
            else if (contrat.getSpecialite()== Specialite.SECURITE)
            {
                chiffreAffaireEntreDeuxDates+=(differenceInmonths*contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesSecurite+=(differenceInmonths*contrat.getMontantContrat());

            }
        }
        log.info("chiffreAffaireEntreDeuxDates: "+chiffreAffaireEntreDeuxDates);
        log.info("chiffreAffaireEntreDeuxDatesIA:" +chiffreAffaireEntreDeuxDatesIA);
        log.info("chiffreAffaireEntreDeuxDatesCloud "+chiffreAffaireEntreDeuxDatesCloud);
        log.info("chiffreAffaireEntreDeuxDatesReseau "+chiffreAffaireEntreDeuxDatesReseau);
        log.info("chiffreAffaireEntreDeuxDatesSecurite "+chiffreAffaireEntreDeuxDatesSecurite);
        return chiffreAffaireEntreDeuxDates;


    }

}