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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.ActivityService;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/group-activities")
public class GroupActivitiesRestController {

    @Autowired
    private ActivityService actServ;

    //GET group activities
    @Operation(summary = "Get all group activities")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the group activity",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Activity.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/")
    public ResponseEntity<List<Activity>> getGroupActivities(){
        return ResponseEntity.ok(actServ.findAll());
    }

    //GET group activity with id
    @Operation(summary = "Get a group activity by id")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the group activity",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Activity.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "group activity not found",
                    content = @Content
            )
    })

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getGroupActivity(@PathVariable Long id){
        Optional<Activity> groupAct = actServ.findById(id);
        if (groupAct.isPresent()){
            return ResponseEntity.ok(groupAct.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    //POST group activity
    @Operation(summary = "Post a group activity")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Activity.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "group activity not found",
                    content = @Content
            )
    })

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        URI location = fromCurrentRequest().build().toUri();
        actServ.save(activity);
        return ResponseEntity.created(location).body(activity);
    }

    //PUT group activity
    @Operation(summary = "PUT group activity")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "group activity found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Activity.class)
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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "group activity not found",
                    content = @Content
            )
    })

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable long id, @RequestBody Activity updatedAct) throws SQLException {

        if (actServ.findById(id).isPresent()){
            Activity dbAct = actServ.findById(id).orElseThrow();
            updatedAct.setImage(BlobProxy.generateProxy(dbAct.getImage().getBinaryStream(),
                    dbAct.getImage().length()));
            updatedAct.setId(id);
            actServ.save(updatedAct);

            return new ResponseEntity<>(updatedAct, HttpStatus.OK);
        } else	{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE group activity
    /*@Operation(summary = "Delete group activity")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "group activity delete",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Activity.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "group activity not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            )
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Activity> deleteGroupActivities(@PathVariable long id) {
        try {
            actServ.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    } */

    //GET group activity image
    @Operation(summary = "Get a group activity image")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the group activity",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Activity.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Image not found",
                    content = @Content
            )
    })

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Activity actGroup = actServ.findById(id).orElseThrow();

        if (actGroup.getImage() != null) {

            Resource file = new InputStreamResource(actGroup.getImage().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(actGroup.getImage().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //PUT group activity image
    @Operation(summary = "PUT a group activity image")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Activity.class)
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

        Activity actGroup = actServ.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri(); // brings URI from the actual get

        if (!imageFile.isEmpty()){
            actGroup.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
            actServ.save(actGroup);
        }


        return ResponseEntity.created(location).build(); // brings url from the image
        // created code is 201
    }

    //validations error
}
