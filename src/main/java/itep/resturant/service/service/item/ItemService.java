package itep.resturant.service.service.item;

import itep.resturant.service.dao.request.ItemRequestDto;
import itep.resturant.service.dao.response.ItemResponseDto;

import java.util.List;

public interface ItemService {
    ItemResponseDto create(long id , ItemRequestDto request);
    List<ItemResponseDto> getByMenuId(long id);
    ItemResponseDto update(long id, ItemRequestDto request);
   String delete(long id);
}
