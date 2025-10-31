package dosw.Masterchef;

import dosw.Masterchef.dto.RecipeDTO;
import dosw.Masterchef.exception.RecipeNotFoundException;
import dosw.Masterchef.mapper.RecipeMapper;
import dosw.Masterchef.model.Recipe;
import dosw.Masterchef.repository.RecipeRepository;
import dosw.Masterchef.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    private RecipeRepository repository;
    private RecipeMapper mapper;
    private RecipeService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(RecipeRepository.class);
        mapper = new RecipeMapper();
        service = new RecipeService(repository, mapper);
    }

    // a. Validar que se pueda registrar una receta
    @Test
    void testSaveRecipe() {
        RecipeDTO dto = new RecipeDTO(null, "Pasta", List.of("queso", "pasta"), List.of("mezclar"), "Ana", "chef", 1);
        Recipe entity = mapper.toEntity(dto);
        entity.setId("1");

        when(repository.save(any(Recipe.class))).thenReturn(entity);

        RecipeDTO saved = service.saveRecipe(dto);

        assertNotNull(saved.getId());
        assertEquals("Pasta", saved.getTitle());
        verify(repository, times(1)).save(any(Recipe.class));
    }

    // b. Validar búsqueda por ingrediente devuelva resultados correctos
    @Test
    void testGetByIngredient() {
        Recipe recipe1 = new Recipe("1", "Pasta", List.of("queso", "pasta"), List.of("mezclar"), "Ana", "chef", 1);
        Recipe recipe2 = new Recipe("2", "Pizza", List.of("queso", "harina"), List.of("hornear"), "Juan", "televidente", 1);

        when(repository.findByIngredientsContaining("queso")).thenReturn(Arrays.asList(recipe1, recipe2));

        List<RecipeDTO> result = service.getByIngredient("queso");

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(r -> r.getIngredients().contains("queso")));
        verify(repository, times(1)).findByIngredientsContaining("queso");
    }

    // c. Validar error al consultar receta inexistente
    @Test
    void testGetById_NotFound() {
        when(repository.findById("999")).thenReturn(Optional.empty());

        assertThrows(RecipeNotFoundException.class, () -> service.getById("999"));
        verify(repository, times(1)).findById("999");
    }
}
