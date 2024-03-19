package itep.resturant.service.service.restaurant;

import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponstDto;

import java.util.List;

public interface RestaurantService {
    RestaurantResponstDto Create(RestaurantRequestDto request);
    List<RestaurantResponstDto> GetAll();
    RestaurantResponstDto Update(long id, RestaurantRequestDto request);
}
