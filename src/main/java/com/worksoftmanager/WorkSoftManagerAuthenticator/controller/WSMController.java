package com.worksoftmanager.WorkSoftManagerAuthenticator.controller;

import com.worksoftmanager.WorkSoftManagerAuthenticator.DTO.UserDTO;
import com.worksoftmanager.WorkSoftManagerAuthenticator.model.LoginRequest;
import com.worksoftmanager.WorkSoftManagerAuthenticator.model.UserSession;

import com.worksoftmanager.WorkSoftManagerAuthenticator.service.WSMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class WSMController {

    private Map<String, UserSession> sessions = new HashMap<>();

    @Autowired
    private WSMService service;


    @CrossOrigin
    @PostMapping(path = "/login", consumes = {"application/json"})
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        String token = null;

        try{
            UserDTO user = authenticate(request);
            if (user == null) {
                return ResponseEntity.badRequest().body("Invalid username or password");
            }
            token = UUID.randomUUID().toString();

            UserSession session = new UserSession(user.getUsername(), token);
            Boolean hasUserSession = service.getUserSession(session);
            if(hasUserSession) {
                service.updateToken(session);
                return ResponseEntity.ok(token);
            }
            service.createUserSession(session);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body("An exception has occurred while trying to Login, please check the server.");
        }
        return ResponseEntity.ok(token);
    }

    @CrossOrigin
    @PostMapping("/logoff")
    public ResponseEntity<String> logoff(@RequestBody UserSession userSession) {
        try {
            service.deleteSession(userSession);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body("An exception has occurred while trying to logoff, please verify the server.");
        }

        return ResponseEntity.ok("User logged off successfully");
    }

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody LoginRequest loginRequest) {
        try{
            service.createUserLogin(loginRequest);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An exception has occurred while trying to create an User, please check the server: " + e.getMessage());
        }

        return ResponseEntity.ok("User created successfully");
    }

    @CrossOrigin
    @PostMapping("/check-token")
    public Boolean getUser(@RequestBody UserSession userSession) {
        Boolean hasUserSession = false;
        try{
            hasUserSession = service.getUserSession(userSession);
        }catch (Exception e){
            return false;
        }
        return hasUserSession;
    }

    private UserDTO authenticate(LoginRequest request) {
        if(!service.getCredentials(request)) {
            return null;
        }

        return new UserDTO(request.getUsername(),request.getPassword());
    }


}
