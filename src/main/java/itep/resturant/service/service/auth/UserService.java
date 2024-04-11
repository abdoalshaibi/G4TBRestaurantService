package itep.resturant.service.service.auth;

import itep.resturant.service.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    User userRestaurantIdService(long id);
}
