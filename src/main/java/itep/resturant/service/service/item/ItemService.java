package itep.resturant.service.service.item;

import itep.resturant.service.service.dto.ItemRequestDto;
import itep.resturant.service.service.dto.ItemResponseDto;

public interface ItemService {
    ItemResponseDto Create(long id ,ItemRequestDto request);
//    List<ItemResponseDto> GetById(long id);
    ItemResponseDto Update(long id,ItemRequestDto request);
//    void Delete(long id);
}
