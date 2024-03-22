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
            restaurant.createdAt = LocalDateTime.now();
            restaurant.createdBy = 1;
            var rest =repository.save(restaurant);
            return mapper.map(rest, RestaurantResponseDto.class);

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

        var restaurant = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No data found in id :"+id));

        mapper.map(request,restaurant);
        restaurant.updatedAt =LocalDateTime.now();
         return mapper.map(repository.save(restaurant), RestaurantResponseDto.class);
    }

    @Override
    public RestaurantResponseDto ChangeStatus(long id, boolean status) {
        var restaurant = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("\"No data found in id :\"+id"));


        restaurant.isOnline = status;

        return mapper.map(repository.save(restaurant), RestaurantResponseDto.class);
    }
}
