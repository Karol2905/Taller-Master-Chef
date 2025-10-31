package dosw.Masterchef.service;

import dosw.Masterchef.dto.RecipeDTO;
import dosw.Masterchef.exception.RecipeNotFoundException;
import dosw.Masterchef.mapper.RecipeMapper;
import dosw.Masterchef.model.Recipe;
import dosw.Masterchef.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    public RecipeService(RecipeRepository repository, RecipeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RecipeDTO saveRecipe(RecipeDTO dto) {
        Recipe saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    public List<RecipeDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public RecipeDTO getById(String id) {
        Recipe recipe = repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Receta no encontrada"));
        return mapper.toDTO(recipe);
    }

    public List<RecipeDTO> getByType(String type) {
        return repository.findByType(type)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecipeDTO> getByIngredient(String ingredient) {
        return repository.findByIngredientsContaining(ingredient)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecipeDTO> getBySeason(Integer season) {
        return repository.findBySeason(season)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        if (!repository.existsById(id))
            throw new RecipeNotFoundException("No existe receta para eliminar");
        repository.deleteById(id);
    }

    public RecipeDTO update(String id, RecipeDTO newRecipe) {
        Recipe existing = repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Receta no encontrada"));

        existing.setTitle(newRecipe.getTitle());
        existing.setIngredients(newRecipe.getIngredients());
        existing.setSteps(newRecipe.getSteps());
        existing.setChefName(newRecipe.getChefName());
        existing.setType(newRecipe.getType());
        existing.setSeason(newRecipe.getSeason());

        Recipe updated = repository.save(existing);
        return mapper.toDTO(updated);
    }
}
