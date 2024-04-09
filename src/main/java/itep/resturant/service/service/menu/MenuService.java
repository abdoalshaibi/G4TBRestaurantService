package itep.resturant.service.service.menu;

import itep.resturant.service.dao.request.MenuRequestDto;
import itep.resturant.service.dao.response.MenuResponseDto;

import java.util.List;

public interface MenuService {
    MenuResponseDto create(long restaurant_id, MenuRequestDto request);
    List<MenuResponseDto> getAllById(long id);
    MenuResponseDto update(long id, MenuRequestDto request);
    String delete(long id);
}
