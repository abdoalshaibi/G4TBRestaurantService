package itep.resturant.service.service.restaurant;

import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    RestaurantRepository repository;
    ModelMapper mapper;

    public RestaurantServiceImpl(RestaurantRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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

        var restaurant = repository.findById(id);

        if (restaurant.isEmpty())
            throw new IllegalArgumentException("Restaurant not found with ID: " + id);


        mapper.map(request,restaurant);
        restaurant.get().UpdatedAt=LocalDateTime.now();
         return mapper.map(repository.save(restaurant.get()), RestaurantResponseDto.class);
    }

    @Override
    public RestaurantResponseDto ChangeStatus(long id, boolean status) {
        var restaurant = repository.findById(id);

        if (restaurant.isEmpty())
            throw new IllegalArgumentException("Restaurant not found with ID: " + id);

        restaurant.get().isOnline = status;

        return mapper.map(repository.save(restaurant.get()), RestaurantResponseDto.class);
    }
}
