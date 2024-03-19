package itep.resturant.service.service.menu;

import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.dto.MenuResponseDto;
import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponstDto;

import java.util.List;

public interface MenuService {
    MenuResponseDto Create(long restaurant_id,MenuRequestDto request);
    List<MenuResponseDto> GetById(long id);
    MenuResponseDto Update(long id, MenuRequestDto request);
    void Delete(long id);
}
