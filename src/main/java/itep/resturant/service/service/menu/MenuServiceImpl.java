package itep.resturant.service.service.menu;

import itep.resturant.service.entity.Menu;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.dto.MenuResponseDto;
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
    public MenuResponseDto Create(long id,MenuRequestDto request) {

        var restaurant=restaurantRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Restaurant not found with ID: " + id));

        var menu = mapper.map(request, Menu.class);

        menu.setRestaurant(restaurant);
        menu.setCreatedBy(1);
        menu.setCreatedAt(LocalDateTime.now());
        return mapper.map(repository.save(menu),MenuResponseDto.class);
    }

    @Override
    public List<MenuResponseDto> GetById(long id) {

        return repository.findAllByRestaurantId(id)
                .stream()
                .map(e->mapper.map(e,MenuResponseDto.class))
                .toList();
    }

    @Override
    public MenuResponseDto Update(long id, MenuRequestDto request) {

        var menu = repository.findById(id);

        if (menu.isEmpty())
            throw new IllegalArgumentException("Restaurant not found with ID: " + id);

        mapper.map(request,menu);

        return mapper.map(repository.save(menu.get()),MenuResponseDto.class);
    }

    @Override
    public void Delete(long id) {

        var menu = repository.findById(id);

        if (menu.isEmpty())
            throw new IllegalArgumentException("Restaurant not found with ID: " + id);

        repository.delete(menu.get());
    }

}
