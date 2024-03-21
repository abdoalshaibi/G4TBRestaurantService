package itep.resturant.service.service.item;

import itep.resturant.service.service.dto.ItemRequestDto;
import itep.resturant.service.service.dto.ItemResponseDto;

import java.util.List;

public interface ItemService {
    ItemResponseDto Create(ItemRequestDto request);
//    List<ItemResponseDto> GetById(long id);
//    ItemResponseDto Update(long id, ItemRequestDto request);
//    void Delete(long id);
}
