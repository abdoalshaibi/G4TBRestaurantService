package itep.resturant.service.controller;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.entity.remote.order.ChangeStatusRequest;
import itep.resturant.service.service.order.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

   private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{restaurant_id}")
    public APIResponse<Object> getAll(@PathVariable UUID restaurant_id)
    {

        return orderService.getAll(restaurant_id);
    }

    @GetMapping("/{restaurant_id}/{order_id}")
    public APIResponse<Object> getAllDetails(@PathVariable UUID restaurant_id,@PathVariable UUID order_id)
    {
        return orderService.getDetails( restaurant_id,  order_id);
    }

    @PostMapping("/{restaurant_id}/{order_id}")
    public APIResponse<Object> getAllDetails(@PathVariable UUID restaurant_id,@PathVariable UUID order_id, @RequestBody ChangeStatusRequest request)
    {
        return orderService.changStatus( restaurant_id,  order_id,  request);
    }

}
