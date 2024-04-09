package itep.resturant.service.service.item;

import itep.resturant.service.dao.request.ItemRequest;
import itep.resturant.service.dao.response.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse create(long id , ItemRequest request);
    List<ItemResponse> getByMenuId(long id);
    ItemResponse update(long id, ItemRequest request);
   String delete(long id);
}
