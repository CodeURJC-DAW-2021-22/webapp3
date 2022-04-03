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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.service.ExerciseService;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/api/exercises")
public class ExerciseRestController {

    @Autowired
    private ExerciseService exerServ;

    //GET exercise
    @Operation(summary = "Get all exercise")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Exercise.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/")
    public ResponseEntity<List<Exercise>> getExercises(Pageable page) {
        return new ResponseEntity<>(exerServ.findPage(page.getPageNumber()).getContent(), HttpStatus.OK);
    }

    //GET exercise by id
    @Operation(summary = "Get a exercise by id")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Exercise.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable long id) {
        Optional<Exercise> exer = exerServ.findById(id);
        if (exer.isPresent()) {
            return ResponseEntity.ok(exer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST new exercise
    @Operation(summary = "Post a new exercise")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Exercise.class)
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
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exer) {
        URI location = fromCurrentRequest().build().toUri();
        exerServ.save(exer);
        return ResponseEntity.created(location).build();
    }

    //PUT exercise
    @Operation(summary = "PUT a exercise")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Exercise.class)
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
    public ResponseEntity<Exercise> updateExercise(@PathVariable long id, @RequestBody Exercise updatedExer) throws SQLException {
        if (exerServ.findById(id).isPresent()){
            if (updatedExer.hasImage()){
                Exercise dbExerTab = exerServ.findById(id).orElseThrow();
                if (dbExerTab.hasImage()) {
                    updatedExer.setImage(BlobProxy.generateProxy(dbExerTab.getImage().getBinaryStream(),
                            dbExerTab.getImage().length()));
                }
            }
            updatedExer.setId(id);
            exerServ.save(updatedExer);
            return new ResponseEntity<>(updatedExer, HttpStatus.OK);
        } else	{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE exercise
    @Operation(summary = "Delete exercise")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "exercise delete",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Exercise.class)
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
    public ResponseEntity<Exercise> deleteExercise(@PathVariable long id) {
        try {
            exerServ.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //GET exercise image
    @Operation(summary = "Get exercise image")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the exercise",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Exercise.class)
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

        Exercise exerTab = exerServ.findById(id).orElseThrow();

        if (exerTab.getImage() != null) {

            Resource file = new InputStreamResource(exerTab.getImage().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(exerTab.getImage().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //PUT exercise id
    @Operation(summary = "Put exercise")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Exercise.class)
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
            )
    })

    @PutMapping("/{id}/image")
    // this method is a PUT because uploading an image in API REST is a form-data type, not a JSON.
    // I can't create an exercise table and introduce an image in the same petition
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        Exercise exerTab = exerServ.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri(); // brings URI from the actual get

        exerTab.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        exerServ.save(exerTab);

        return ResponseEntity.created(location).build(); // brings url from the image
        // created code is 201
    }


}