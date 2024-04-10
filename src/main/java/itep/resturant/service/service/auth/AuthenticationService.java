package itep.resturant.service.service.auth;


import itep.resturant.service.dao.request.SignUpRequest;
import itep.resturant.service.dao.request.SigninRequest;
import itep.resturant.service.dao.response.JwtAuthenticationResponse;
import itep.resturant.service.entity.Restaurant;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);
    void signup(Restaurant restaurant, SignUpRequest request);
    JwtAuthenticationResponse signin(SigninRequest request);
    long extractClaims();
}