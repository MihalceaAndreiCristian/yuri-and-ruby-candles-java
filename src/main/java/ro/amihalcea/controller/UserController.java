package ro.amihalcea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.amihalcea.dto.LoginDTO;
import ro.amihalcea.dto.UserDTO;
import ro.amihalcea.model.UserPrincipal;
import ro.amihalcea.service.UserService;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginDTO user){
        var userSaved = service.createUser(user);

        return ResponseEntity.ok(String.format("User %s created with success!",userSaved.getUsername()));
    }

    @GetMapping("/details")
    public ResponseEntity<UserDTO> getUserDetails(Authentication authentication){
        String username = ((UserPrincipal)authentication.getPrincipal()).getUsername();
        return ResponseEntity.ok(service.getUserDetails(username));
    }


}
