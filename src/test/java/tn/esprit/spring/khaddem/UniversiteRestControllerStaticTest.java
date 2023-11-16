package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.khaddem.entities.Universite;

import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

 class UniversiteRestControllerStaticTest {




    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddUniversite() {



        Universite newUniversite = new Universite();
        newUniversite.setIdUniversite(1);
        newUniversite.setNomUniv("Esprit");


        when(universiteRepository.save(newUniversite)).thenReturn(newUniversite);

        Universite addedUniversite = universiteService.addUniversite(newUniversite);

        assertEquals(newUniversite, addedUniversite);

        verify(universiteRepository, times(1)).save(newUniversite);

        System.err.println("testAddUniversite : SUCCESS");

    }

    @Test
    void testUpdateUniversite() {
        Universite existingUniversite = new Universite();
        existingUniversite.setIdUniversite(1);

        when(universiteRepository.save(existingUniversite)).thenReturn(existingUniversite);

        Universite updatedUniversite = universiteService.updateUniversite(existingUniversite);

        assertEquals(existingUniversite, updatedUniversite);

        verify(universiteRepository, times(1)).save(existingUniversite);

        System.err.println("testUpdateUniversite : SUCCESS");
    }

    @Test
    void testRetrieveUniversite() {
        int id = 1;
        Universite simpleUniversite = new Universite();
        simpleUniversite.setIdUniversite(id);

        when(universiteRepository.findById(id)).thenReturn(Optional.of(simpleUniversite));

        Universite result = universiteService.retrieveUniversite(id);

        assertEquals(simpleUniversite, result);

        verify(universiteRepository, times(1)).findById(id);

        System.err.println("testRetrieveUniversite : SUCCESS");
    }


}