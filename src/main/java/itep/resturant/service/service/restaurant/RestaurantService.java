package itep.resturant.service.service.restaurant;

import itep.resturant.service.dao.request.RestaurantRequestDto;
import itep.resturant.service.dao.response.RestaurantResponseDto;

import java.util.List;

public interface RestaurantService {
    RestaurantResponseDto Create(long id ,RestaurantRequestDto request);
    List<RestaurantResponseDto> GetAll();
    RestaurantResponseDto Update(long id, RestaurantRequestDto request);
    RestaurantResponseDto ChangeStatus(long id, boolean status);
    List<RestaurantResponseDto> getByCuisineId(long id);
}
