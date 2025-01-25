package ro.amihalcea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.amihalcea.model.UserModel;
import ro.amihalcea.service.UserService;

@RestController("users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserModel user){
        var userSaved = service.createUser(user);

        return ResponseEntity.ok(String.format("User %s created with success!",userSaved.getUsername()));
    }
}
