package itep.resturant.service.repository.remote;

import itep.resturant.service.entity.remote.order.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
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
                            @Value("${external.api.order.auth.password}")String password) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        this.email = email;
        this.password = password;
    }

    private Optional<GenericResponse<Auth>> login(OrderAuthRequest request) {

        var x= restClient
                .post()
                .uri("/auth/login")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<GenericResponse<Auth>>() {});

        return Optional.ofNullable(x);
    }

    @Override
    public Optional<Object> getAll(UUID restaurant_id) {

        var auth = login(new  OrderAuthRequest(email,password))
                .orElseThrow(()-> new IllegalArgumentException(""));

        var x= restClient
                .get()
                .uri("/restaurants/{restaurantId}",restaurant_id)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(auth.data().access_token()))
                .retrieve()
                .body(new ParameterizedTypeReference<GenericResponse<OrderResponse>>() {
                });


        return Optional.ofNullable(x != null ? x.data() : new IllegalArgumentException("No data found"));
    }
    @Override
    public Optional<Object> getDetails(UUID restaurant_id,UUID order_id) {

        var auth = login(new OrderAuthRequest(email,password))
                .orElseThrow(()-> new IllegalArgumentException(""));

        return restClient.get()
                .uri("/restaurants/{RestaurantUuid}/orders/{OrderUuid}}/details",restaurant_id,order_id)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(auth.data().access_token()))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public Optional<Object> changStatus(UUID restaurant_id, UUID order_id, ChangeStatusRequest request) {

        var auth = login(new OrderAuthRequest(email,password))
                .orElseThrow(()-> new IllegalArgumentException(""));

        var result = restClient.post()
                .uri("/restaurants/{RestaurantUuid}/orders/{OrderUuid}",restaurant_id,order_id)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(auth.data().access_token()))
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<GenericResponse<Order>>() {});

        return Optional.of(result != null ? result : new IllegalArgumentException("No data found"));
    }
}
