package webapp3.webapp3.controller;

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

    @GetMapping("/")
    public ResponseEntity<List<Activity>> getGroupActivities(){
        return ResponseEntity.ok(actServ.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getGroupActivity(@PathVariable Long id){
        Optional<Activity> groupAct = actServ.findById(id);
        if (groupAct.isPresent()){
            return ResponseEntity.ok(groupAct.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@RequestBody Activity activity) {
        actServ.save(activity);
        return activity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable long id, @RequestBody Activity updatedAct) throws SQLException {

        if (actServ.findById(id).isPresent()){
            if (updatedAct.hasImage()){
                Activity dbAct = actServ.findById(id).orElseThrow();
                if (dbAct.hasImage()) {
                    updatedAct.setImage(BlobProxy.generateProxy(dbAct.getImage().getBinaryStream(),
                            dbAct.getImage().length()));
                }
            }
            updatedAct.setId(id);
            actServ.save(updatedAct);

            return new ResponseEntity<>(updatedAct, HttpStatus.OK);
        } else	{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Activity> deleteGroupActivities(@PathVariable long id) {
        try {
            actServ.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

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

    @PutMapping("/{id}/image")
    // this method is a PUT because uploading an image in API REST is a form-data type, not a JSON.
    // I can't create an exercise table and introduce an image in the same petition
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        Activity actGroup = actServ.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri(); // brings URI from the actual get

        actGroup.hasImage();
        actGroup.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        actServ.save(actGroup);

        return ResponseEntity.created(location).build(); // brings url from the image
        // created code is 201
    }

    //validations error
}
