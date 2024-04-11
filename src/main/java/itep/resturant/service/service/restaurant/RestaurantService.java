package itep.resturant.service.service.restaurant;

import itep.resturant.service.dao.request.RestaurantRequest;
import itep.resturant.service.dao.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse Create(long id , RestaurantRequest request);
    List<RestaurantResponse> GetAll();
    RestaurantResponse Get();
    RestaurantResponse Update(long id, RestaurantRequest request);
    RestaurantResponse ChangeStatus(long id, boolean status);
    List<RestaurantResponse> getByCuisineId(long id);
}
