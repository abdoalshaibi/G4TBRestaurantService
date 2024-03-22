package itep.resturant.service.service.restaurant;

import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;

import java.util.List;

public interface RestaurantService {
    RestaurantResponseDto Create(long id ,RestaurantRequestDto request);
    List<RestaurantResponseDto> GetAll();
    RestaurantResponseDto Update(long id, RestaurantRequestDto request);
    RestaurantResponseDto ChangeStatus(long id, boolean status);
    List<RestaurantResponseDto> getByCuisineId(long id);
}
