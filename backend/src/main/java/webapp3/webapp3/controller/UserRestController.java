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
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService usrServ;

    //GET log monitor
    @JsonView(User.MonitorLog.class)
    @GetMapping("/monitors/me")
    public ResponseEntity<User> monitorLog(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(usrServ.findByEmail(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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

    //GET log member
    @JsonView(User.MemberLog.class)
    @GetMapping("/members/me")
    public ResponseEntity<User> membersLog(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(usrServ.findByEmail(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET log user image
    @GetMapping("/me/image")
    public ResponseEntity<Object> meImage(HttpServletRequest request) throws SQLException {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            Optional<User> optionalUser = usrServ.findByEmail(principal.getName());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getImage() != null) {
                    Resource file = new InputStreamResource(user.getImage().getBinaryStream());
                    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                            .contentLength(user.getImage().length()).body(file);
                }
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST members
    @PostMapping("/members/new/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createMember(@RequestBody User user) {
        if (user.getUserType().equals("member")) {
            usrServ.save(user);
            URI location = fromCurrentRequest().path("/members/{id}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).body(user);
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
            URI location = fromCurrentRequest().path("/monitors/{id}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).body(user);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //PUT monitor image
    @PutMapping("/monitors/{id}/image/")
    // this method is a PUT because uploading an image in API REST is a form-data type, not a JSON.
    // I can't create an exercise table and introduce an image in the same petition
    public ResponseEntity<Object> uploadMonitorImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        User mon = usrServ.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();


        mon.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        usrServ.save(mon);

        return ResponseEntity.created(location).build();

    }

    //PUT user log image
    @PutMapping("/me/image/")
    // this method is a PUT because uploading an image in API REST is a form-data type, not a JSON.
    // I can't create an exercise table and introduce an image in the same petition
    public ResponseEntity<Object> uploadMyImage(HttpServletRequest request, @RequestParam MultipartFile imageFile) throws IOException {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            User me = usrServ.findByEmail(principal.getName()).orElseThrow();

            URI location = fromCurrentRequest().build().toUri();

            me.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
            usrServ.save(me);

            return ResponseEntity.created(location).build();

        }else {
            return ResponseEntity.notFound().build();
        }
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

    //PUT user log
    @PutMapping("/me/")
    public ResponseEntity<User> updateMe(HttpServletRequest request, @RequestBody User updatedUser) throws SQLException {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            if (updatedUser.getImage() != null) {
                User dbUser = usrServ.findByEmail(principal.getName()).orElseThrow();
                if (dbUser.getImage() != null) {
                    updatedUser.setImage(BlobProxy.generateProxy(dbUser.getImage().getBinaryStream(),
                            dbUser.getImage().length()));
                }
            }

            updatedUser.setId(usrServ.findByEmail(principal.getName()).get().getId());
            usrServ.save(updatedUser);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }else{
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
