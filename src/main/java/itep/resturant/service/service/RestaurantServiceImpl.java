package itep.resturant.service.service;

import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository repository;

    @Autowired 
    private ModelMapper mapper;

    @Override
    public RestaurantResponstDto Create(RestaurantRequestDto request) {

        Restaurant restaurant = mapper.map(request, Restaurant.class);

        return mapper.map(repository.save(restaurant), RestaurantResponstDto.class);
    }

    @Override
    public List<RestaurantResponstDto> GetAll() {

        return repository.findAll()
                .stream()
                .map(e -> mapper.map(e, RestaurantResponstDto.class))
                .toList();
    }

    @Override
    public RestaurantResponstDto Update(long id, RestaurantRequestDto request) {


        Restaurant restaurant = repository.findById(id).get();

        restaurant.setUpdatedAt(LocalDateTime.now());
        mapper.map(request, restaurant);

        return mapper.map(repository.save(restaurant), RestaurantResponstDto.class);
    }

    @Override
    public RestaurantResponstDto ChangeStatus(long id, boolean status) {
        var restaurant = repository.findById(id).get();
        restaurant.setOnline(status);
        return mapper.map(repository.save(restaurant), RestaurantResponstDto.class);
    }
}
