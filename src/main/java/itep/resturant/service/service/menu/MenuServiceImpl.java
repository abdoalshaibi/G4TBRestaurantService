package itep.resturant.service.service.menu;

import itep.resturant.service.entity.Menu;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.dto.MenuResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository repository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public MenuResponseDto Create(long restaurant_id,MenuRequestDto request) {
        var menu = mapper.map(request, Menu.class);
        menu.setRestaurant(restaurantRepository.findById(restaurant_id).get());
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

        var menu = repository.findById(id).get();
        mapper.map(request,menu);
        //menu.setRestaurant(restaurantRepository.findById(id).get());
        return mapper.map(repository.save(menu),MenuResponseDto.class);
    }

    @Override
    public void Delete(long id) {

        var menu = repository.findById(id).get();
         repository.delete(menu);
    }

}
