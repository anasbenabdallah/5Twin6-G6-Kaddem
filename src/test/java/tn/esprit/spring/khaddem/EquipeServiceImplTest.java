package tn.esprit.spring.khaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class EquipeServiceTest {
    @Mock
    EquipeRepository equipeRepository;
    @Mock
    ContratRepository contratRepository;
    @InjectMocks
    EquipeServiceImpl equipeService;
    Equipe equipe1 = new Equipe(1, "Equipe 1", Niveau.JUNIOR);
    Equipe equipe2 = new Equipe(2, "Equipe 2", Niveau.SENIOR);
    List<Equipe> equipes = Arrays.asList(equipe1, equipe2);

    @Test
    void testRetrieveAllEquipes() {
        // Arrange

        Mockito.when(equipeRepository.findAll()).thenReturn(equipes);

        // Act
        List<Equipe> retrievedEquipes = equipeService.retrieveAllEquipes();

        // Assert
        assertEquals(2, retrievedEquipes.size());
        assertEquals("Equipe 1", retrievedEquipes.get(0).getNomEquipe());
        assertEquals("Equipe 2", retrievedEquipes.get(1).getNomEquipe());

    }

    @Test
    void testAddEquipe() {
        // Arrange
        Equipe newEquipe = new Equipe("New Equipe", Niveau.JUNIOR);

        Mockito.when(equipeRepository.save(any(Equipe.class))).thenReturn(newEquipe);

        // Act
        Equipe addedEquipe = equipeService.addEquipe(newEquipe);

        // Assert
        assertNotNull(addedEquipe);
        assertEquals("New Equipe", addedEquipe.getNomEquipe());
        verify(equipeRepository, times(1)).save(any(Equipe.class));
    }

    @Test
    void updateEquipe() {
        // Arrange
        Equipe existingEquipe = new Equipe("Existing Equipe", Niveau.JUNIOR);
        Equipe updatedEquipeData = new Equipe("Updated Equipe", Niveau.SENIOR);

        Mockito.when(equipeRepository.findById(existingEquipe.getIdEquipe())).thenReturn(Optional.of(existingEquipe));
        Mockito.when(equipeRepository.save(existingEquipe)).thenReturn(existingEquipe);

        // Act
        Equipe updatedEquipe = equipeService.updateEquipe(updatedEquipeData);

        // Assert
        assertNotNull(updatedEquipe);
        assertEquals("Updated Equipe", updatedEquipe.getNomEquipe());
    }


    @Test
    void retrieveEquipe() {
        // Arrange
        int EquipetId = 5;
        Equipe newEquipe = new Equipe(EquipetId, "New Equipe 5", Niveau.JUNIOR);

        Mockito.when(equipeRepository.findById(EquipetId)).thenReturn(Optional.of(newEquipe));

        // Act
        Equipe retrievedEquipe = equipeService.retrieveEquipe(EquipetId);

        // Assert
        assertNotNull(retrievedEquipe);
        assertEquals("New Equipe 5", retrievedEquipe.getNomEquipe());
    }
    @Test
    void testRetrieveAllEquipesWhenEmpty() {
        // Arrange
        Mockito.when(equipeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Equipe> retrievedEquipes = equipeService.retrieveAllEquipes();

        // Assert
        assertTrue(retrievedEquipes.isEmpty());
    }
    @Test
    void testAddEquipeWithNull() {
        // Arrange
        Mockito.when(equipeRepository.save(null)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> equipeService.addEquipe(null));
    }

    @Test
    void retrieveEquipeWhenNotFound() {
        // Arrange
        int EquipetId = 5;

        Mockito.when(equipeRepository.findById(EquipetId)).thenReturn(Optional.empty());

        // Act
        Equipe retrievedEquipe = equipeService.retrieveEquipe(EquipetId);

        // Assert
        assertNull(retrievedEquipe);
    }


}