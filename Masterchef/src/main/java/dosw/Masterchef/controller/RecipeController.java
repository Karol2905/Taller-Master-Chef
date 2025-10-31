package dosw.Masterchef.controller;

import dosw.Masterchef.dto.RecipeDTO;
import dosw.Masterchef.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de recetas de cocina.
 * Permite registrar, consultar, actualizar y eliminar recetas
 * de televidentes, participantes y chefs del programa.
 */
@RestController
@RequestMapping("/api/recipes")
@Tag(name = "Recetas", description = "Operaciones CRUD sobre las recetas de cocina")
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    // ------------------ CREAR ------------------
    @Operation(
            summary = "Registrar una nueva receta",
            description = "Permite crear una receta de un televidente, participante o chef.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Receta creada exitosamente",
                            content = @Content(schema = @Schema(implementation = RecipeDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<RecipeDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la receta a crear",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RecipeDTO.class))
            )
            @RequestBody RecipeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRecipe(dto));
    }

    // ------------------ CONSULTAR TODO ------------------
    @Operation(
            summary = "Listar todas las recetas",
            description = "Devuelve todas las recetas registradas en la base de datos.",
            responses = @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida correctamente")
    )
    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ------------------ CONSULTAR POR ID ------------------
    @Operation(
            summary = "Obtener una receta por ID",
            description = "Devuelve la receta asociada al identificador proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Receta encontrada"),
                    @ApiResponse(responseCode = "404", description = "Receta no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getById(
            @Parameter(description = "Identificador único de la receta") @PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ------------------ CONSULTAR POR TIPO ------------------
    @Operation(
            summary = "Listar recetas por tipo",
            description = "Obtiene las recetas creadas por televidentes, participantes o chefs.",
            responses = @ApiResponse(responseCode = "200", description = "Lista de recetas filtradas")
    )
    @GetMapping("/type/{type}")
    public ResponseEntity<List<RecipeDTO>> getByType(
            @Parameter(description = "Tipo de autor de la receta (televidente, participante, chef)")
            @PathVariable String type) {
        return ResponseEntity.ok(service.getByType(type));
    }

    // ------------------ CONSULTAR POR INGREDIENTE ------------------
    @Operation(
            summary = "Buscar recetas por ingrediente",
            description = "Devuelve todas las recetas que contienen un ingrediente específico.",
            responses = @ApiResponse(responseCode = "200", description = "Lista de recetas encontradas")
    )
    @GetMapping("/ingredient/{ingredient}")
    public ResponseEntity<List<RecipeDTO>> getByIngredient(
            @Parameter(description = "Ingrediente a buscar en las recetas")
            @PathVariable String ingredient) {
        return ResponseEntity.ok(service.getByIngredient(ingredient));
    }

    // ------------------ CONSULTAR POR TEMPORADA ------------------
    @Operation(
            summary = "Listar recetas por temporada",
            description = "Devuelve las recetas de los participantes en una temporada específica.",
            responses = @ApiResponse(responseCode = "200", description = "Lista de recetas por temporada")
    )
    @GetMapping("/season/{season}")
    public ResponseEntity<List<RecipeDTO>> getBySeason(
            @Parameter(description = "Número de temporada") @PathVariable Integer season) {
        return ResponseEntity.ok(service.getBySeason(season));
    }

    // ------------------ ACTUALIZAR ------------------
    @Operation(
            summary = "Actualizar una receta existente",
            description = "Permite modificar los datos de una receta registrada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Receta actualizada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Receta no encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> update(
            @Parameter(description = "ID de la receta a actualizar") @PathVariable String id,
            @RequestBody RecipeDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // ------------------ ELIMINAR ------------------
    @Operation(
            summary = "Eliminar una receta",
            description = "Elimina la receta asociada al ID proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Receta eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Receta no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la receta a eliminar") @PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
