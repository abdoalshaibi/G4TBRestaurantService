package itep.resturant.service.service.auth;


import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.dao.request.SigninRequest;
import itep.resturant.service.dao.request.SignUpRequest;
import itep.resturant.service.dao.response.JwtAuthenticationResponse;
import itep.resturant.service.entity.local.Restaurant;
import itep.resturant.service.entity.local.Role;
import itep.resturant.service.entity.local.User;
import itep.resturant.service.repository.local.UserRepository;
import itep.resturant.service.util.Constant;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public APIResponse<JwtAuthenticationResponse> signup(SignUpRequest request) {

        try {
            var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                    .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN).build();

            userRepository.save(user);

            var jwt = jwtService.generateToken(generateExtraClaims(user), user);

            return APIResponse.ok(JwtAuthenticationResponse.builder().token(jwt).build(), Constant.getLogResponseHashMap(),"USER-".concat("7"));

        }catch (Exception ex)
        {
            if (ex.getMessage().contains("users_email_key"))
                return APIResponse.badRequest(null, Constant.getLogResponseHashMap(),"USER-".concat("1"));

            return APIResponse.badRequest(null, Constant.getLogResponseHashMap(),"USER-".concat("10"));

        }
    }

    @Override
    public void signup(Restaurant restaurant, SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();

        user.setRestaurant(restaurant);
        userRepository.save(user);

        var jwt = jwtService.generateToken(generateExtraClaims(user), user);
        JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(generateExtraClaims(user), user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public long extractClaims() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the claims from the authentication details
        if (authentication != null && authentication.getPrincipal() instanceof User user) {

            return user.getId();
        }

        return 0;
    }

    private HashMap<String, Object> generateExtraClaims(User user) {
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Id", user.getId());
        extraClaims.put("role", user.getRole().name());
        return extraClaims;
    }

}