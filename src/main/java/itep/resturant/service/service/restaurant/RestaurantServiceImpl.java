package itep.resturant.service.service.restaurant;

import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;
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
    ModelMapper mapper;

    @Override
    public RestaurantResponseDto Create(RestaurantRequestDto request) {

        var restaurant = mapper.map(request, Restaurant.class);
        restaurant.CreatedAt = LocalDateTime.now();
        return mapper.map(repository.save(restaurant), RestaurantResponseDto.class);
    }

    @Override
    public List<RestaurantResponseDto> GetAll() {

        return repository.findAll()
                .stream()
                .map(e->mapper.map(e, RestaurantResponseDto.class))
                .toList();


    }

    @Override
    public RestaurantResponseDto Update(long id, RestaurantRequestDto request) {

        var restaurant = repository.findById(id).get();
        mapper.map(request,restaurant);
        restaurant.UpdatedAt=LocalDateTime.now();
         return mapper.map(repository.save(restaurant), RestaurantResponseDto.class);
    }

    @Override
    public RestaurantResponseDto ChangeStatus(long id, boolean status) {
        var restaurant = repository.findById(id).get();
        restaurant.isOnline = status;
        return mapper.map(repository.save(restaurant), RestaurantResponseDto.class);
    }
}
