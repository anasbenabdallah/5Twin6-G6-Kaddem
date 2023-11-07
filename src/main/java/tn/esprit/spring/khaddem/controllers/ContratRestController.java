package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.ContratDTO;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.services.IContratService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")

@RequestMapping("/contrat")
public class ContratRestController {
    IContratService contratService;

    // http://localhost:8089/Kaddem/contrat/retrieve-all-contrats
    @GetMapping("/retrieve-all-contrats")
    @ResponseBody
    public List<ContratDTO> getContrats() {
        List<Contrat> listContrats = contratService.retrieveAllContrats();
        return listContrats.stream()
                .map(contrat -> new ContratDTO(
                        contrat.getIdContrat(),
                        contrat.getDateDebutContrat(),
                        contrat.getDateFinContrat(),
                        contrat.getSpecialite(),
                        contrat.getArchived(),
                        contrat.getMontantContrat()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-contrat/{contrat-id}")
    @ResponseBody
    public ContratDTO retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
        Contrat contrat = contratService.retrieveContrat(contratId);
        return new ContratDTO(
                contrat.getIdContrat(),
                contrat.getDateDebutContrat(),
                contrat.getDateFinContrat(),
                contrat.getSpecialite(),
                contrat.getArchived(),
                contrat.getMontantContrat()
        );
    }

    @PostMapping("/add-contrat")
    @ResponseBody
    public ContratDTO addContrat(@RequestBody ContratDTO c) {
        Contrat contrat = new Contrat(
                c.getIdContrat(),
                c.getDateDebutContrat(),
                c.getDateFinContrat(),
                c.getSpecialite(),
                c.getArchived(),
                c.getMontantContrat()
        );
        contrat = contratService.addContrat(contrat);
        return new ContratDTO(
                contrat.getIdContrat(),
                contrat.getDateDebutContrat(),
                contrat.getDateFinContrat(),
                contrat.getSpecialite(),
                contrat.getArchived(),
                contrat.getMontantContrat()
        );
    }
    @PutMapping("/update-contrat")
    @ResponseBody
    public ContratDTO updateContrat(@RequestBody ContratDTO cont) {
        Contrat contrat = new Contrat(
                cont.getIdContrat(),
                cont.getDateDebutContrat(),
                cont.getDateFinContrat(),
                cont.getSpecialite(),
                cont.getArchived(),
                cont.getMontantContrat()
        );
        contrat = contratService.updateContrat(contrat);
        return new ContratDTO(
                contrat.getIdContrat(),
                contrat.getDateDebutContrat(),
                contrat.getDateFinContrat(),
                contrat.getSpecialite(),
                contrat.getArchived(),
                contrat.getMontantContrat()
        );
    }

    @PostMapping("/addAndAffectContratToEtudiant/{nomE}/{prenomE}")
    @ResponseBody
    public ContratDTO addAndAffectContratToEtudiant(@RequestBody ContratDTO contrat, @PathVariable("nomE") String nomE, @PathVariable("prenomE") String prenomE) {
        Contrat contratEntity = new Contrat(
                contrat.getIdContrat(),
                contrat.getDateDebutContrat(),
                contrat.getDateFinContrat(),
                contrat.getSpecialite(),
                contrat.getArchived(),
                contrat.getMontantContrat()
        );
        contratEntity = contratService.addAndAffectContratToEtudiant(contratEntity, nomE, prenomE);
        return new ContratDTO(
                contratEntity.getIdContrat(),
                contratEntity.getDateDebutContrat(),
                contratEntity.getDateFinContrat(),
                contratEntity.getSpecialite(),
                contratEntity.getArchived(),
                contratEntity.getMontantContrat()
        );
    }



    //The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
    @GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
    public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.nbContratsValides(startDate, endDate);
    }

    //Only no-arg methods may be annotated with @Scheduled
    @Scheduled(cron="0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    //  @Scheduled(cron="45 * * * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    @PutMapping(value = "/majStatusContrat")
    public void majStatusContrat (){
        contratService.retrieveAndUpdateStatusContrat();
    }



    //public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate)

    @GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    @ResponseBody
    public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                    @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }

}
