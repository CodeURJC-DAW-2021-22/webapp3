package webapp3.webapp3.controller;

import com.fasterxml.jackson.annotation.JsonView;
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
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.UserExerciseTableService;
import webapp3.webapp3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService usrServ;

    @Autowired
    private UserExerciseTableService usExServ;

    //GET log monitor
    @Operation(summary = "Get monitor logged in the application")

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the monitor",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
            )}
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Not found",
                content = @Content
        )
    })

    @JsonView(User.MonitorLog.class)
    @GetMapping("/monitors/me")
    public ResponseEntity<User> monitorLog(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            return ResponseEntity.ok(usrServ.findByEmail(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET monitors
    @Operation(summary = "Get all monitors")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the monitor",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @JsonView(User.MonitorBasic.class)
    @GetMapping("/monitors")
    public ResponseEntity<List<User>> getMonitors() {
        return new ResponseEntity<>(usrServ.findByUserType("monitor"), HttpStatus.OK);
    }

    //GET monitor with id
    @Operation(summary = "Get a monitor by id")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the monitor",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "monitor not found",
                    content = @Content
            )
    })

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
    @Operation(summary = "Get a monitor image")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the image",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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
    @Operation(summary = "Get all members")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the members",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @JsonView(User.MemberBasic.class)
    @GetMapping("/members")
    public ResponseEntity<List<User>> getMembers() {
        return new ResponseEntity<>(usrServ.findByUserType("member"), HttpStatus.OK);
    }

    //GET log member
    @Operation(summary = "Get member logged in the application")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the member",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @JsonView(User.MemberLog.class)
    @GetMapping("/members/me")
    public ResponseEntity<User> membersLog(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            return ResponseEntity.ok(usrServ.findByEmail(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET log user image
    @Operation(summary = "Get the logged member image")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the image",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })

    @GetMapping("/me/image")
    public ResponseEntity<Object> meImage(HttpServletRequest request) throws SQLException {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
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
    @Operation(summary = "Post a new member")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            )
    })

    @PostMapping("/members/new/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createMember(@RequestBody User user) {
        if (user.getUserType().equals("member")) {
            usrServ.save(user);
            URI location = fromCurrentRequest().path("/members/{id}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).body(user);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //POST monitors
    @Operation(summary = "Post a new monitor")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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

    @PostMapping("/monitors/new/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createMonitor(@RequestBody User user) {
        if (user.getUserType().equals("monitor")) {
            usrServ.save(user);
            URI location = fromCurrentRequest().path("/monitors/{id}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).body(user);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //PUT monitor image
    @Operation(summary = "PUT a monitor image")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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
    @Operation(summary = "PUT a monitor image")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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
    @Operation(summary = "PUT monitor")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monitor found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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
                    description = "Not found",
                    content = @Content
            )
    })

    @PutMapping("/monitors/{id}/")
    public ResponseEntity<User> updateMonitor(@PathVariable long id, @RequestBody User updatedUser) throws SQLException {
        if (usrServ.exist(id)) {
            if (usrServ.findById(id).get().getUserType().equals("monitor") && updatedUser.getUserType().equals("monitor")) {
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
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //PUT user log
    @Operation(summary = "Put user logged")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "user found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE monitor
    @Operation(summary = "Delete monitor")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "monitor delete",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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
    @DeleteMapping("/monitors/{id}")
    public ResponseEntity<User> deleteMonitor(@PathVariable long id) {
        if (usrServ.findById(id).get().getUserType().equals("monitor")) {
            try {
                usrServ.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);

            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE member
    @Operation(summary = "Delete member")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "member delete",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=User.class)
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
    @DeleteMapping("/members/{id}")
    public ResponseEntity<User> deleteMembers(@PathVariable long id) {
        if (usrServ.findById(id).get().getUserType().equals("member")) {
            try {
                usrServ.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);

            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //Admin statistics
    @Operation(summary = "Get admin statistics")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })
    @GetMapping("/admin/statistics")
    public ResponseEntity<ArrayList<ArrayList<Integer>>> adminStats(){
        return new ResponseEntity<>(usrServ.getStatistics(), HttpStatus.OK);
    }

    //Member statistics
    @Operation(summary = "Get members statistics")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            )
    })
    @GetMapping("/members/statistics")
    public ResponseEntity<HashMap<String, Integer>> memberStats(HttpServletRequest request){
        User user = usrServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow();
        return new ResponseEntity<>(usExServ.findExercisesTables(user.getId()), HttpStatus. OK);
    }
}
