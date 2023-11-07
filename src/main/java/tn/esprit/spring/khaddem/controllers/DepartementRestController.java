package tn.esprit.spring.khaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.services.IDepartementService;

import java.util.List;

@RestController
@RequestMapping("/departement")

public class DepartementRestController {
    private final IDepartementService departementService;

    @Autowired
    public DepartementRestController(IDepartementService departementService) {
        this.departementService = departementService;
    }
    @GetMapping("/retrieve-all-departements")
    @ResponseBody
    public List<Departement> getDepartements() {
        return departementService.retrieveAllDepartements();
    }


    @GetMapping("/retrieve-departement/{departement-id}")
    @ResponseBody
    public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }

    @PostMapping("/add-departement")
    @ResponseBody
    public Departement addDepartement(@RequestBody Departement d) {
        departementService.addDepartement(d);
        return d;
    }

    @PutMapping("/update-departement")
    @ResponseBody
    public Departement updateDepartement(@RequestBody Departement departement) {
        return departementService.updateDepartement(departement);
    }




    @GetMapping("/retrieveDepartementsByUniversite/{idUniversite}")
    @ResponseBody
    public List<Departement> retrieveDepartementsByUniversite(@PathVariable("idUniversite") Integer idUniversite) {
        return departementService.retrieveDepartementsByUniversite(idUniversite);
    }


}
