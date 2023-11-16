package tn.esprit.spring.khaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")

public class EtudiantRestController {

    private final IEtudiantService etudiantService;

    @Autowired
    public EtudiantRestController(IEtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }
    // http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
    @GetMapping("/retrieve-all-etudiants")
    @ResponseBody
    public List<Etudiant> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }
    // http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-etudiant/{etudiantId}")
    @ResponseBody
    public Etudiant retrieveContrat(@PathVariable("etudiantId") Integer etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

    // http://localhost:8089/Kaddem/etudiant/add-etudiant
    @PostMapping("/add-etudiant")
    @ResponseBody
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return etudiantService.addEtudiant(e);
    }


    // http://localhost:8089/Kaddem/etudiant/update-etudiant
    @PutMapping("/update-etudiant")
    @ResponseBody
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {
        return etudiantService.updateEtudiant(e);
    }
    // http://localhost:8089/Kaddem/etudiant/removeEtudiant
    @DeleteMapping("/removeEtudiant/{idEtudiant}")
    @ResponseBody
    public void removeEtudiant(@PathVariable("idEtudiant") Integer idEtudiant) {
        etudiantService.removeEtudiant(idEtudiant);
    }


}