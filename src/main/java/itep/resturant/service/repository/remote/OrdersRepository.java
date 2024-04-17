package itep.resturant.service.repository.remote;

import itep.resturant.service.entity.remote.ChangeStatusRequest;
import itep.resturant.service.entity.remote.OrderAuthRequest;
import itep.resturant.service.entity.remote.OrderAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;



@Repository
public class OrdersRepository implements OrderRepository {

    private final RestClient restClient;
    private final String email;
    private final String password;

    public OrdersRepository(@Value("${external.api.order.url}") String baseUrl,
                            @Value("${external.api.order.auth.email}") String email,
                            @Value("${external.api.order.auth.email}")String password) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        this.email = email;
        this.password = password;
    }

    private Optional<OrderAuthResponse> login(OrderAuthRequest request) {

        return restClient
                .post()
                .uri("/api/auth")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public Optional<Object> getAll(UUID restaurant_id) {

        var auth = login(new  OrderAuthRequest(email,password))
                .orElseThrow(()-> new IllegalArgumentException(""));


        return restClient
                .get()
               .uri("/restaurants/{RestaurantUuid}?pageNo=0&pageSize=10",restaurant_id)
                .header("Authorization",auth.accessToken())
               .retrieve()
               .body(new ParameterizedTypeReference<>() {});
    }
    @Override
    public Optional<Object> getDetails(UUID restaurant_id,UUID order_id) {

        var auth = login(new OrderAuthRequest(email,password))
                .orElseThrow(()-> new IllegalArgumentException(""));

        return restClient.get()
                .uri("/restaurants/{RestaurantUuid}/orders/{OrderUuid}}/details",restaurant_id,order_id)
                .header("Authorization",auth.accessToken())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public Optional<Object> changStatus(UUID restaurant_id,UUID order_id, ChangeStatusRequest request) {

        var auth = login(new OrderAuthRequest(email,password))
                .orElseThrow(()-> new IllegalArgumentException(""));

        return restClient.post()
                .uri("/restaurants/{RestaurantUuid}/orders/{OrderUuid}",restaurant_id,order_id)
                .header("Authorization",auth.accessToken())
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
