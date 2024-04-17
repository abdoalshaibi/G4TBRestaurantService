package itep.resturant.service.service.menu;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.dao.request.MenuRequest;
import itep.resturant.service.dao.response.MenuResponse;

import java.util.List;

public interface MenuService {
    APIResponse<MenuResponse> create(long restaurant_id, MenuRequest request);
    APIResponse<List<MenuResponse>> getAllById(long id);
    APIResponse<MenuResponse> update(long id, MenuRequest request);
    APIResponse<String> delete(long id);
}
