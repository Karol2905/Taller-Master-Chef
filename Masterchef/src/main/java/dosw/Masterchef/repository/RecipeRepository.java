package dosw.Masterchef.repository;



import dosw.Masterchef.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByType(String type);
    List<Recipe> findByIngredientsContaining(String ingredient);
    List<Recipe> findBySeason(Integer season);
}

