package itep.resturant.service.service.restaurant;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.dao.request.RestaurantRequest;
import itep.resturant.service.dao.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    APIResponse<RestaurantResponse> Create(long id , RestaurantRequest request);
    APIResponse<List<RestaurantResponse>> GetAll();
    APIResponse<RestaurantResponse> Get();
    APIResponse<RestaurantResponse> Update(long id, RestaurantRequest request);
    APIResponse<RestaurantResponse> ChangeStatus(long id, boolean status);
    APIResponse<List<RestaurantResponse>> getByCuisineId(long id);
}
