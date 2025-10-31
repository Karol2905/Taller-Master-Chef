package dosw.Masterchef.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recipes")
public class Recipe {
    @Id
    private String id;
    private String title;
    private List<String> ingredients;
    private List<String> steps;
    private String chefName;
    private String type;
    private Integer season;


}
