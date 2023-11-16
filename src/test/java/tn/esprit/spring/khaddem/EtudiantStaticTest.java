package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import java.util.*;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
 class EtudiantStaticTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;
    @Mock
    private EtudiantRepository etudiantRepository;
    // You need to create ContratRepository for data access

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Create a list of sample Etudiants
        List<Etudiant> sampleEtudiants = new ArrayList<>();

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        etudiant1.setNomE("Taha");
        etudiant1.setPrenomE("JEMLI");
        etudiant1.setOp(Option.GAMIX);
        sampleEtudiants.add(etudiant1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setIdEtudiant(2);
        etudiant2.setNomE("Tarek");
        etudiant2.setPrenomE("AYADI");
        etudiant2.setOp(Option.INFINI);
        sampleEtudiants.add(etudiant2);

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setIdEtudiant(2);
        etudiant3.setNomE("Aziz");
        etudiant3.setPrenomE("Charrada");
        etudiant3.setOp(Option.SE);
        sampleEtudiants.add(etudiant3);



        // Mock the behavior of the EtudiantsRepository to return the sampleEtudiants when retrieveAllEtudiants is called
        when(etudiantRepository.findAll()).thenReturn(sampleEtudiants);

        // Call the service method
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Verify that the service method returned the expected data
        assertEquals(sampleEtudiants, result);

        System.err.println("testRetrieveAllEtudiants : SUCCESS");
    }

    @Test
    void testAddEtudiant() {
        // Create a sample Etudiants to add
        Etudiant newetudiant = new Etudiant();
        newetudiant.setIdEtudiant(2);
        newetudiant.setNomE("Tarek");
        newetudiant.setPrenomE("AYADI");
        newetudiant.setOp(Option.INFINI);

        // Mock the behavior of the EtudiantsRepository to save the neEtudiants and return it
        when(etudiantRepository.save(newetudiant)).thenReturn(newetudiant);

        // Call the service method
        Etudiant addedEtudiant = etudiantService.addEtudiant(newetudiant);

        // Verify that the service method returned the added Etudiants
        assertEquals(newetudiant, addedEtudiant);

        // Verify that the save method was called on the EtudiantsRepository
        verify(etudiantRepository, times(1)).save(newetudiant);

        System.err.println("testAddEtudiant : SUCCESS");

    }
    @Test
    void testUpdateEtudiant() {
        // Create a sample Etudiants to update
        Etudiant existingEtudiant = new Etudiant();
        existingEtudiant.setIdEtudiant(1);
        // Set other properties as needed

        // Mock the behavior of the EtudiantsRepository to save the updated Etudiants and return it
        when(etudiantRepository.save(existingEtudiant)).thenReturn(existingEtudiant);

        // Call the service method
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(existingEtudiant);

        // Verify that the service method returned the updated Etudiantss
        assertEquals(existingEtudiant, updatedEtudiant);

        // Verify that the save method was called on theEtudiantsRepository
        verify(etudiantRepository, times(1)).save(existingEtudiant);

        System.err.println("testUpdateEtudiant : SUCCESS");
    }
    @Test
    void testRetrieveEtudiant() {
        // Create a sample Etudiants with a known ID
        int id = 1;
        Etudiant sampleEtudiant = new Etudiant();
        sampleEtudiant.setIdEtudiant(id);
        // Set other properties as needed

        // Mock the behavior of the EtudiantsRepository to return the sampleEtudiants when findById is called
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(sampleEtudiant));

        // Call the service method
        Etudiant result = etudiantService.retrieveEtudiant(id);

        // Verify that the service method returned the expected Etudiants
        assertEquals(sampleEtudiant, result);

        // Verify that the findById method was called on the EtudiantsRepository
        verify(etudiantRepository, times(1)).findById(id);

        System.err.println("testRetrieveContrat : SUCCESS");
    }
    @Test
    void testRemoveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        etudiantService.removeEtudiant(etudiant.getIdEtudiant());
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();
        assertEquals(0, result.size());

        System.err.println("DeleteEtudiantTest : SUCCESS");
    }
}