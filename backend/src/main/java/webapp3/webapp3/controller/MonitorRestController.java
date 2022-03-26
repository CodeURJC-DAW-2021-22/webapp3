package webapp3.webapp3.controller;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class MonitorRestController {

    @Autowired
    private UserService usrServ;

    @GetMapping("/")
    public List<User> getUsers() {
        return usrServ.findAll();
    }

    //ayuda
    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(usrServ.findByName(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        Optional<User> op = usrServ.findById(id);
        if (op.isPresent()) {
            User user = op.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {

        usrServ.save(user);

        return user;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) throws SQLException {

        if (usrServ.exist(id)) {

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
        } else    {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}