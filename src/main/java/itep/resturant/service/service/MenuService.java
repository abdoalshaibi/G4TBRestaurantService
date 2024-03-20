package itep.resturant.service.service;

import java.util.List;

public interface MenuService {

    MenuResponseDto Create(MenuRequestDto request);
    List<MenuResponseDto> GetAll();
    MenuResponseDto Update(long id, MenuRequestDto request);
    
}
