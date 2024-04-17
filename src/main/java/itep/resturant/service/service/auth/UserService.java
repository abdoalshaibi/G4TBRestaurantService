package itep.resturant.service.service.auth;

import itep.resturant.service.entity.local.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    User userRestaurantIdService(long id);
}
