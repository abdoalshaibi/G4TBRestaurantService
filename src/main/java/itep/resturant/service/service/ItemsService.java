package itep.resturant.service.service;

import java.util.List;

public interface ItemsService {

    ItemsResponseDto Create(ItemsRequestDto request);
    List<ItemsResponseDto> GetAll();
    ItemsResponseDto Update(long id, ItemsRequestDto request);
    
}
