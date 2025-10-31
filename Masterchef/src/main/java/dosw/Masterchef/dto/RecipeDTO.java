package dosw.Masterchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private String id;
    private String title;
    private List<String> ingredients;
    private List<String> steps;
    private String chefName;
    private String type;
    private Integer season;
}
