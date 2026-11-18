package controller;

import dto.RegisterUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.RegistrationService;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {
    private final RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService){
        this.registrationService=registrationService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUser userDetail){
        registrationService.registerUser(userDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body("User has been registered");
    }

}
