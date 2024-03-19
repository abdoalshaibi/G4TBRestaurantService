package itep.resturant.service.service.restaurant;

import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponstDto;
import itep.resturant.service.service.restaurant.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository repository;
    ModelMapper mapper;

    @Override
    public RestaurantResponstDto Create(RestaurantRequestDto request) {

        var restaurant = mapper.map(request, Restaurant.class);

        return mapper.map(repository.save(restaurant),RestaurantResponstDto.class);
    }

    @Override
    public List<RestaurantResponstDto> GetAll() {

        return repository.findAll()
                .stream()
                .map(e->mapper.map(e,RestaurantResponstDto.class))
                .toList();


    }

    @Override
    public RestaurantResponstDto Update(long id, RestaurantRequestDto request) {


        var restaurant = repository.findById(id).get();


        mapper.map(request,restaurant);

         return mapper.map(repository.save(restaurant),RestaurantResponstDto.class);
    }
}
