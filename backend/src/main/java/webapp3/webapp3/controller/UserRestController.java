package webapp3.webapp3.controller;

import com.fasterxml.jackson.annotation.JsonView;
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
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.UserService;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService usrServ;

    //GET monitors
    @JsonView(User.MonitorBasic.class)
    @GetMapping("/monitors")
    public List<User> getMonitors() {
        return usrServ.findByUserType("monitor");
    }

    //GET monitor with id
    @GetMapping("/monitors/{id}")
    public ResponseEntity<User> getMonitor(@PathVariable long id) {
        Optional<User> op = usrServ.findById(id);
        if (op.isPresent()) {
            User monitor = op.get();
            return new ResponseEntity<>(monitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET monitor image
    @GetMapping("/monitors/{id}/image")
    public ResponseEntity<Object> downloadMonitorImage(@PathVariable long id) throws SQLException {
        Optional<User> optionalUser = usrServ.findById(id);
        if (optionalUser.isPresent() && optionalUser.get().getUserType().equals("monitor")) {
            User user = optionalUser.get();
            if (user.getImage() != null) {
                Resource file = new InputStreamResource(user.getImage().getBinaryStream());
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(user.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    //GET members
    @JsonView(User.MemberBasic.class)
    @GetMapping("/members")
    public List<User> getMembers() {
        return usrServ.findByUserType("member");
    }

    //POST members
    @PostMapping("/members/new/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createMember(@RequestBody User user) {
        if (user.getUserType().equals("member")) {
            usrServ.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //POST monitors
    @PostMapping("/monitors/new/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createMonitor(@RequestBody User user) {
        if (user.getUserType().equals("monitor")) {
            usrServ.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //POST monitor image
    @PostMapping("/monitors/{id}/image/")
    public ResponseEntity<Object> uploadMonitorImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {
        User mon = usrServ.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();

        mon.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        usrServ.save(mon);

        return ResponseEntity.created(location).build();
    }

    //PUT monitor
    @PutMapping("/monitors/{id}/")
    public ResponseEntity<User> updateMonitor(@PathVariable long id, @RequestBody User updatedUser) throws SQLException {
        if (usrServ.exist(id)) {
            if(usrServ.findById(id).get().getUserType().equals("monitor") && updatedUser.getUserType().equals("monitor")){
                if (updatedUser.getImage() != null) {
                    User dbUser = usrServ.findById(id).orElseThrow();
                    if (dbUser.getImage() != null) {
                        updatedUser.setImage(BlobProxy.generateProxy(dbUser.getImage().getBinaryStream(),
                                dbUser.getImage().length()));
                    }
                }

                updatedUser.setId(id);
                usrServ.save(updatedUser);

                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else	{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE monitor
    @DeleteMapping("/monitors/{id}")
    public ResponseEntity<User> deleteMonitor(@PathVariable long id) {
        if(usrServ.findById(id).get().getUserType().equals("monitor")){
            try {
                usrServ.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);

            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE member
    @DeleteMapping("/members/{id}")
    public ResponseEntity<User> deleteMembers(@PathVariable long id) {
        if(usrServ.findById(id).get().getUserType().equals("member")){
            try {
                usrServ.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);

            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}