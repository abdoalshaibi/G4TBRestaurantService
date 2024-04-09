package itep.resturant.service.service;


import itep.resturant.service.dao.request.SignUpRequest;
import itep.resturant.service.dao.request.SigninRequest;
import itep.resturant.service.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}