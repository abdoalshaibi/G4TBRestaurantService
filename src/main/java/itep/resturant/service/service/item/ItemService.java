package itep.resturant.service.service.item;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.dao.request.ItemRequest;
import itep.resturant.service.dao.response.ItemResponse;

import java.util.List;

public interface ItemService {
    APIResponse<ItemResponse> create(long id , ItemRequest request);
    APIResponse<List<ItemResponse>> getByMenuId(long id);
    APIResponse<ItemResponse> update(long id, ItemRequest request);
    APIResponse<String> delete(long id);
}
