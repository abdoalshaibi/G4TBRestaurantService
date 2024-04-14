package itep.resturant.service.service.menu;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.entity.Menu;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.dao.request.MenuRequest;
import itep.resturant.service.dao.response.MenuResponse;
import itep.resturant.service.service.auth.AuthenticationService;
import itep.resturant.service.util.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final AuthenticationService authenticationService;
    private final ModelMapper mapper;

    public MenuServiceImpl(MenuRepository repository, RestaurantRepository restaurantRepository, AuthenticationService authenticationService, ModelMapper mapper) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @Override
    public APIResponse<MenuResponse> create(long id, MenuRequest request) {

        var restaurantOptional = restaurantRepository.findById(id);

        if (restaurantOptional.isEmpty())
           return APIResponse.notFound(null,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("5"));


        var menu = mapper.map(request, Menu.class);

        menu.setRestaurant(restaurantOptional.get());
        menu.setCreatedAt(LocalDateTime.now());
        menu.setCreatedBy(authenticationService.extractClaims());

        var response = mapper.map(repository.save(menu), MenuResponse.class);

        return APIResponse.ok(response, Constant.getLogResponseHashMap(),"MENU-".concat("7"));
    }

    @Override
    public APIResponse<List<MenuResponse>> getAllById(long id) {

        var menus = repository.findAllByRestaurantId(id)
                .stream()
                .map(e->mapper.map(e, MenuResponse.class))
                .toList();

        return APIResponse.ok(menus,Constant.getLogResponseHashMap(),"MENU-".concat("1"));
    }

    @Override
    public APIResponse<MenuResponse> update(long id, MenuRequest request) {

        var optionalMenu = repository.findById(id);

        if (optionalMenu.isEmpty())
           return APIResponse.notFound(null,Constant.getLogResponseHashMap(),"MENU-".concat(""));

        var menu = optionalMenu.get();

         mapper.map(request,menu);

        menu.setCreatedAt(LocalDateTime.now());
        menu.setCreatedBy(authenticationService.extractClaims());

        var response =  mapper.map(repository.save(menu), MenuResponse.class);

        return APIResponse.ok(response,Constant.getLogResponseHashMap(),"MENU-".concat("1"));

    }

    @Override
    public APIResponse<String> delete(long id) {

        var menu = repository.findById(id);

        if (menu.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(), "MENU-".concat("1"));

        repository.delete(menu.get());

        return APIResponse.ok(null, Constant.getLogResponseHashMap(), "MENU-".concat("3"));

    }
}
