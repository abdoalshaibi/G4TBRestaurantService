package itep.resturant.service.repository.remote;

import itep.resturant.service.entity.remote.OrderAuthRequest;
import itep.resturant.service.entity.remote.OrderAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Repository
public class DriverRepositoryImpl implements DriverRepository {
    private final RestClient restClient;
    private final String email;
    private final String password;

    public DriverRepositoryImpl(@Value("${external.api.driver.url}") String baseUrl,
                                @Value("${external.api.driver.auth.email}") String email,
                                @Value("${external.api.driver.auth.password}") String password) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        this.email = email;
        this.password = password;
    }

    @Override
    public Optional<OrderAuthResponse> login(OrderAuthRequest request) {

        return restClient
                .post()
                .uri("/api/auth")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }


    @Override
    public Optional<Object> getAll()
    {

        var auth = login(new  OrderAuthRequest(email,password))
                .orElseThrow(()-> new IllegalArgumentException(""));

        return restClient.get()
                .uri("/api/Driver?pageNo=0&pageSize=10")
                .header("Authorization",auth.accessToken())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
