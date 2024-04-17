package itep.resturant.service.Configuration;

import itep.resturant.service.entity.local.Permission;
import itep.resturant.service.service.auth.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, UserService userService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request -> {
            request.requestMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll();
            request.requestMatchers(HttpMethod.POST, "/api/v1/auth/signin").permitAll();
            request.requestMatchers("/api/v1/orders/**").hasAuthority(Permission.GET_ALL_ORDERS.name());


            // cuisine
            request.requestMatchers(HttpMethod.POST, "/api/v1/cuisine").hasAuthority(Permission.SAVE_ONE_CUISINE.name());
            request.requestMatchers(HttpMethod.DELETE, "/api/v1/cuisine").hasAuthority(Permission.DELETE_ONE_CUISINE.name());
            request.requestMatchers(HttpMethod.PUT, "/api/v1/cuisine/{id}").hasAuthority(Permission.UPDATE_ONE_CUISINE.name());

            // Item
            request.requestMatchers(HttpMethod.POST, "/api/v1/item/{id}").hasAuthority(Permission.SAVE_ONE_ITEM.name());
            request.requestMatchers(HttpMethod.PUT, "/api/v1/item/{id}").hasAuthority(Permission.UPDATE_ONE_ITEM.name());
            request.requestMatchers(HttpMethod.DELETE, "/api/v1/item/{id}").hasAuthority(Permission.DELETE_ONE_ITEM.name());

            // Menu
            request.requestMatchers(HttpMethod.POST, "/api/v1/menu/{id}").hasAuthority(Permission.SAVE_ONE_MENU.name());
            request.requestMatchers(HttpMethod.PUT, "/api/v1/menu/{id}").hasAuthority(Permission.UPDATE_ONE_MENU.name());
            request.requestMatchers(HttpMethod.DELETE, "/api/v1/menu/{id}").hasAuthority(Permission.DELETE_ONE_MENU.name());

            // Restaurant
            request.requestMatchers(HttpMethod.POST, "/api/v1/restaurant/{id}").hasAuthority(Permission.SAVE_ONE_RESTAURANT.name());
            request.requestMatchers(HttpMethod.PUT, "/api/v1/restaurant/{id}").hasAuthority(Permission.UPDATE_ONE_RESTAURANT.name());



            request.anyRequest().permitAll();
        }).sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS)).authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
