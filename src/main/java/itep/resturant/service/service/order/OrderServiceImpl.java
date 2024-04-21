package itep.resturant.service.service.order;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.entity.remote.order.ChangeStatusRequest;
import itep.resturant.service.repository.remote.OrderRepository;
import itep.resturant.service.util.Constant;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public APIResponse<Object> getAll(UUID id) {

        var ordersOptional = orderRepository.getAll(id);

        if (ordersOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(),"");

        var orders = ordersOptional.get();

        return APIResponse.ok(orders, Constant.getLogResponseHashMap(),"");
    }

    @Override
    public APIResponse<Object> getDetails(UUID restaurant_id, UUID order_id) {

        var orderOptional = orderRepository.getDetails(restaurant_id,order_id);
        if (orderOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(),"");

        var orders = orderOptional.get();

        return APIResponse.ok(orders, Constant.getLogResponseHashMap(),"");
    }

    @Override
    public APIResponse<Object> changStatus(UUID restaurant_id, UUID order_id, ChangeStatusRequest request) {

        var orderOptional = orderRepository.changStatus(restaurant_id,order_id,request);

        if (orderOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(),"");

        var orders = orderOptional.get();

        return APIResponse.ok(orders, Constant.getLogResponseHashMap(),"");
    }
}
