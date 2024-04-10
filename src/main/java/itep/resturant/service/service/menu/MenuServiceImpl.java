package itep.resturant.service.service.menu;

import itep.resturant.service.entity.Menu;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.dao.request.MenuRequest;
import itep.resturant.service.dao.response.MenuResponse;
import itep.resturant.service.service.auth.AuthenticationService;
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
    public MenuResponse create(long id, MenuRequest request) {

        var restaurant=restaurantRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Restaurant not found with ID: " + id));

        var menu = mapper.map(request, Menu.class);

        menu.setRestaurant(restaurant);

        menu.setCreatedAt(LocalDateTime.now());
        menu.setCreatedBy(authenticationService.extractClaims());

        return mapper.map(repository.save(menu), MenuResponse.class);
    }

    @Override
    public List<MenuResponse> getAllById(long id) {

        return repository.findAllByRestaurantId(id)
                .stream()
                .map(e->mapper.map(e, MenuResponse.class))
                .toList();
    }

    @Override
    public MenuResponse update(long id, MenuRequest request) {

        var menu = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Restaurant not found with ID: " + id));

        mapper.map(request,menu);

        menu.setCreatedAt(LocalDateTime.now());
        menu.setCreatedBy(authenticationService.extractClaims());

        return mapper.map(repository.save(menu), MenuResponse.class);
    }

    @Override
    public String delete(long id) {

        var menu = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Restaurant not found with ID: " + id));

        repository.delete(menu);

        return "Menu with Id " + id + "deleted";
    }

}
