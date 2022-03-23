package webapp3.webapp3.controller;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.ExerciseTableService;
import webapp3.webapp3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercises-tables")
public class ExerciseTableRestController {

    @Autowired
    private ExerciseTableService exerTabServ;

    @Autowired
    private UserService usrServ;

    // getting all exercise tables
    @GetMapping("/")
    public ResponseEntity<List<ExerciseTable>> getExerciseTables(){
        return ResponseEntity.ok(exerTabServ.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseTable> getExerciseTable(@PathVariable Long id){
        Optional<ExerciseTable> exerTab = exerTabServ.findById(id);
        if (exerTab.isPresent()){
            return ResponseEntity.ok(exerTab.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<?> pdfGenerator(@PathVariable Long id, HttpServletRequest request){
        try {
            String emailName = request.getUserPrincipal().getName();
            Optional<User> mem = usrServ.findByEmail(emailName);
            User user = mem.orElseThrow();
            ByteArrayOutputStream baos = exerTabServ.generatePDF(user.getId(), id);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-disposition", "attachment;filename=\"TablaDeEjercicios.pdf\"")
                    .body(baos.toByteArray());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseTable createExerciseTable(@RequestBody ExerciseTable exerciseTable) {
        exerTabServ.save(exerciseTable);

        return exerciseTable;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseTable> updateExerciseTable(@PathVariable long id, @RequestBody ExerciseTable updatedExerTab) throws SQLException {

        if (exerTabServ.findById(id).isPresent()){
            if (updatedExerTab.hasImage()){
                ExerciseTable dbExerTab = exerTabServ.findById(id).orElseThrow();
                if (dbExerTab.hasImage()) {
                    updatedExerTab.setImage(BlobProxy.generateProxy(dbExerTab.getImage().getBinaryStream(),
                            dbExerTab.getImage().length()));
                }
            }
            updatedExerTab.setId(id);
            exerTabServ.save(updatedExerTab);

            return new ResponseEntity<>(updatedExerTab, HttpStatus.OK);
        } else	{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //validations error

    //management images (add[post], modify[put] y get[get])

}
