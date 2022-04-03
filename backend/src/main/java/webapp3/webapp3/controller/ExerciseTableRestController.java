package webapp3.webapp3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.ExerciseTableService;
import webapp3.webapp3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/exercises-tables")
public class ExerciseTableRestController {

    @Autowired
    private ExerciseTableService exerTabServ;

    @Autowired
    private UserService usrServ;

    // GET getting all exercise tables

    @Operation(summary = "Get all exercises tables")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the exercises tables",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/")
    public ResponseEntity<List<ExerciseTable>> getExerciseTables() {
        return ResponseEntity.ok(exerTabServ.findAll());
    }

    //GET getExerciseTable
    @Operation(summary = "Get exercise table")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found exercise table",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseTable> getExerciseTable(@PathVariable Long id) {
        Optional<ExerciseTable> exerTab = exerTabServ.findById(id);
        if (exerTab.isPresent()) {
            return ResponseEntity.ok(exerTab.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get pdf generated")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "pdf generated",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/{id}/pdf")
    public ResponseEntity<?> pdfGenerator(@PathVariable Long id, HttpServletRequest request) {
        return exerTabServ.getPdf(id, request);
    }

    //POST  exerciseTable
    @Operation(summary = "Post a new exercise table")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            )
    })

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ExerciseTable> createExerciseTable(@RequestBody ExerciseTable exerciseTable) {
        URI location = fromCurrentRequest().build().toUri();
        exerTabServ.save(exerciseTable);
        return ResponseEntity.created(location).build();
    }

    //PUT updateExerciseTables
    @Operation(summary = "PUT a exercise table")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseTable> updateExerciseTable(@PathVariable long id, @RequestBody ExerciseTable updatedExerTab) throws SQLException {

        if (exerTabServ.findById(id).isPresent()) {
            if (updatedExerTab.hasImage()) {
                ExerciseTable dbExerTab = exerTabServ.findById(id).orElseThrow();
                if (dbExerTab.hasImage()) {
                    updatedExerTab.setImage(BlobProxy.generateProxy(dbExerTab.getImage().getBinaryStream(),
                            dbExerTab.getImage().length()));
                }
            }
            updatedExerTab.setId(id);
            exerTabServ.save(updatedExerTab);

            return new ResponseEntity<>(updatedExerTab, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE exerciseTable
    @Operation(summary = "Delete ExerciseTable")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Exercise Table delete",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ExerciseTable> deleteExerciseTable(@PathVariable long id) {
        try {
            exerTabServ.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //GET downloadImage
    @Operation(summary = "Get image downloaded")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "image downloaded",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        ExerciseTable exerTab = exerTabServ.findById(id).orElseThrow();

        if (exerTab.getImage() != null) {

            Resource file = new InputStreamResource(exerTab.getImage().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(exerTab.getImage().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //PUT uploadImage
    @Operation(summary = "PUT a image")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseTable.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            )
    })
    @PutMapping("/{id}/image")
    // this method is a PUT because uploading an image in API REST is a form-data type, not a JSON.
    // I can't create an exercise table and introduce an image in the same petition
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        ExerciseTable exerTab = exerTabServ.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri(); // brings URI from the actual get


        exerTab.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        exerTabServ.save(exerTab);

        return ResponseEntity.created(location).build(); // brings url from the image
        // created code is 201
    }

    //validations error

}
