package itep.resturant.service.service.restaurant;

import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.dao.request.RestaurantRequest;
import itep.resturant.service.dao.response.RestaurantResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    RestaurantRepository repository;
    CuisineRepository cuisineRepository;
    ModelMapper mapper;

    public RestaurantServiceImpl(RestaurantRepository repository, CuisineRepository cuisineRepository, ModelMapper mapper) {
        this.repository = repository;
        this.cuisineRepository = cuisineRepository;
        this.mapper = mapper;
    }


    @Override
    public RestaurantResponse Create(long id, RestaurantRequest request) {

        var cuisine =cuisineRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("No data found in id " + id));

            var restaurant = mapper.map(request, Restaurant.class);

            restaurant.setCreatedAt(LocalDateTime.now());
            restaurant.setCreatedBy(1);
            restaurant.setCuisine(cuisine);

            var rest =repository.save(restaurant);
            return mapper.map(rest, RestaurantResponse.class);

    }

    @Override
    public List<RestaurantResponse> GetAll() {

        return repository.findAll()
                .stream()
                .map(e->mapper.map(e, RestaurantResponse.class))
                .toList();

    }

    @Override
    public RestaurantResponse Update(long id, RestaurantRequest request) {

        var restaurant = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No data found in id :"+id));

        mapper.map(request,restaurant);
        restaurant.updatedAt =LocalDateTime.now();
         return mapper.map(repository.save(restaurant), RestaurantResponse.class);
    }

    @Override
    public RestaurantResponse ChangeStatus(long id, boolean status) {
        var restaurant = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("\"No data found in id :\"+id"));


        restaurant.isOnline = status;

        return mapper.map(repository.save(restaurant), RestaurantResponse.class);
    }

    @Override
    public List<RestaurantResponse> getByCuisineId(long id) {
         var restaurant = repository.findByCuisineId(id)
                 .orElseThrow(()-> new IllegalArgumentException("No data found in id" + id));

        return restaurant.stream()
                .map(e-> mapper.map(e, RestaurantResponse.class))
                .toList();
    }
}
