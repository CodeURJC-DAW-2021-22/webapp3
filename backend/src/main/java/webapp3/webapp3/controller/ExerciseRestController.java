package webapp3.webapp3.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.net.URI;

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
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.service.ExerciseService;


@RestController
@RequestMapping("/api/exercises")
public class ExerciseRestController {

    @Autowired
    private ExerciseService exerServ;

    @GetMapping("/")
    public ResponseEntity<List<Exercise>> getExercises() {
        return ResponseEntity.ok(exerServ.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable long id) {
        Optional<Exercise> exer = exerServ.findById(id);
        if (exer.isPresent()) {
            return ResponseEntity.ok(exer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exer) {
        URI location = fromCurrentRequest().build().toUri();
        exerServ.save(exer);
        return ResponseEntity.created(location).build();
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable long id) {
        try {
            exerServ.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

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

    @PutMapping("/{id}/image")
    // this method is a PUT because uploading an image in API REST is a form-data type, not a JSON.
    // I can't create an exercise table and introduce an image in the same petition
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        Exercise exerTab = exerServ.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri(); // brings URI from the actual get

        exerTab.hasImage();
        exerTab.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        exerServ.save(exerTab);

        return ResponseEntity.created(location).build(); // brings url from the image
        // created code is 201
    }


}