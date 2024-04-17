package itep.resturant.service.service.order;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.entity.remote.ChangeStatusRequest;

import java.util.UUID;

public interface OrderService {
    APIResponse<Object> getAll(UUID id);
    APIResponse<Object> getDetails(UUID restaurant_id, UUID order_id);
    APIResponse<Object> changStatus(UUID restaurant_id, UUID order_id, ChangeStatusRequest request);
}
