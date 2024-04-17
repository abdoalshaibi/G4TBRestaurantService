package itep.resturant.service.repository.remote;

import itep.resturant.service.entity.remote.OrderAuthRequest;
import itep.resturant.service.entity.remote.OrderAuthResponse;

import java.util.Optional;

public interface DriverRepository {
    Optional<OrderAuthResponse> login(OrderAuthRequest request);

    Optional<Object> getAll();
}
