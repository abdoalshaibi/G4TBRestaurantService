package itep.resturant.service.service.menu;

import itep.resturant.service.entity.Menu;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.dao.request.MenuRequestDto;
import itep.resturant.service.dao.response.MenuResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    MenuRepository repository;
    RestaurantRepository restaurantRepository;
    ModelMapper mapper;

    public MenuServiceImpl(MenuRepository repository, RestaurantRepository restaurantRepository, ModelMapper mapper) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    @Override
    public MenuResponseDto create(long id, MenuRequestDto request) {

        var restaurant=restaurantRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Restaurant not found with ID: " + id));

        var menu = mapper.map(request, Menu.class);

        menu.setRestaurant(restaurant);
        menu.setCreatedBy(1);
        menu.setCreatedAt(LocalDateTime.now());
        return mapper.map(repository.save(menu),MenuResponseDto.class);
    }

    @Override
    public List<MenuResponseDto> getAllById(long id) {

        return repository.findAllByRestaurantId(id)
                .stream()
                .map(e->mapper.map(e,MenuResponseDto.class))
                .toList();
    }

    @Override
    public MenuResponseDto update(long id, MenuRequestDto request) {

        var menu = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Restaurant not found with ID: " + id));

        mapper.map(request,menu);

        return mapper.map(repository.save(menu),MenuResponseDto.class);
    }

    @Override
    public String delete(long id) {

        var menu = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Restaurant not found with ID: " + id));

        repository.delete(menu);

        return "Menu with Id " + id + "deleted";
    }

}
