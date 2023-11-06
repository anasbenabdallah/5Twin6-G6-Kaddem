package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.IEquipeService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
    IEquipeService equipeService;
    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {
        return equipeService.retrieveAllEquipes();
    }



    @GetMapping("/retrieve-equipe/{equipe-id}")
    @ResponseBody
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }


    @PostMapping("/add-equipe")
    @ResponseBody
    public Equipe addEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        convertDTOToEquipe(equipeDTO, equipe);
        return equipeService.addEquipe(equipe);
    }

    @PutMapping("/update-equipe")
    @ResponseBody
    public Equipe updateEtudiant(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        convertDTOToEquipe(equipeDTO, equipe);
        return equipeService.updateEquipe(equipe);
    }
    private void convertDTOToEquipe(EquipeDTO equipeDTO, Equipe equipe) {
        BeanUtils.copyProperties(equipeDTO, equipe);
    }



   @Scheduled(cron="* * 13 * * *")
    @PutMapping("/faireEvoluerEquipes")
    public void faireEvoluerEquipes() {
        equipeService.evoluerEquipes() ;
    }

}
