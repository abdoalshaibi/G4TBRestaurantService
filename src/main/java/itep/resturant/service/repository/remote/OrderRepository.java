package itep.resturant.service.repository.remote;

import itep.resturant.service.entity.remote.order.ChangeStatusRequest;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Object> getAll(UUID id);
    Optional<Object> getDetails(UUID restaurant_id,UUID order_id);
    Optional<Object> changStatus(UUID restaurant_id,UUID order_id, ChangeStatusRequest request);
}
