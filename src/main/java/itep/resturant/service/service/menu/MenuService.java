package itep.resturant.service.service.menu;

import itep.resturant.service.dao.request.MenuRequest;
import itep.resturant.service.dao.response.MenuResponse;

import java.util.List;

public interface MenuService {
    MenuResponse create(long restaurant_id, MenuRequest request);
    List<MenuResponse> getAllById(long id);
    MenuResponse update(long id, MenuRequest request);
    String delete(long id);
}
