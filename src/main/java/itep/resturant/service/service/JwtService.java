package itep.resturant.service.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtService {
    String extractUserName(String token);
    String extractUserid(String token);

    String generateToken(HashMap<String,Object> hashMap, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
