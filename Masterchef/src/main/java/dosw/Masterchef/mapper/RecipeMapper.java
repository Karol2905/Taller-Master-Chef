package dosw.Masterchef.mapper;


import dosw.Masterchef.dto.RecipeDTO;
import dosw.Masterchef.model.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

    public RecipeDTO toDTO(Recipe recipe) {
        if (recipe == null) return null;
        return new RecipeDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getIngredients(),
                recipe.getSteps(),
                recipe.getChefName(),
                recipe.getType(),
                recipe.getSeason()
        );
    }

    public Recipe toEntity(RecipeDTO dto) {
        if (dto == null) return null;
        return new Recipe(
                dto.getId(),
                dto.getTitle(),
                dto.getIngredients(),
                dto.getSteps(),
                dto.getChefName(),
                dto.getType(),
                dto.getSeason()
        );
    }
}
