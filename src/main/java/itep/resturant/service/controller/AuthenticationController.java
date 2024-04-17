package itep.resturant.service.controller;


import itep.resturant.service.dao.request.SignUpRequest;
import itep.resturant.service.dao.request.SigninRequest;
import itep.resturant.service.dao.response.JwtAuthenticationResponse;
import itep.resturant.service.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignUpRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signup(request));
        }catch (Exception ex)
        {
            if (ex.getMessage().contains("users_email_key"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already Exist");
            return ResponseEntity.status(HttpStatus.OK).body("Please ensure that the data is correct");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
