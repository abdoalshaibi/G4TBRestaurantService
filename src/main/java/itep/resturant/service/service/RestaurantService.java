package itep.resturant.service.service;

import java.util.List;

public interface RestaurantService {
    RestaurantResponstDto Create(RestaurantRequestDto request);
    List<RestaurantResponstDto> GetAll();
    RestaurantResponstDto Update(long id, RestaurantRequestDto request);
}
