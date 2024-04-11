package itep.resturant.service.service.restaurant;

import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.dao.request.RestaurantRequest;
import itep.resturant.service.dao.response.RestaurantResponse;
import itep.resturant.service.service.auth.AuthenticationService;
import itep.resturant.service.service.auth.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

   private final RestaurantRepository repository;
    private final CuisineRepository cuisineRepository;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    ModelMapper mapper;

    public RestaurantServiceImpl(RestaurantRepository repository, CuisineRepository cuisineRepository, AuthenticationService authenticationService, UserService userService, ModelMapper mapper) {
        this.repository = repository;
        this.cuisineRepository = cuisineRepository;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.mapper = mapper;
    }


    @Override
    public RestaurantResponse Create(long id, RestaurantRequest request) {

        var cuisine =cuisineRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("No data found in id " + id));

            var restaurant = mapper.map(request, Restaurant.class);

            restaurant.setCreatedAt(LocalDateTime.now());
            restaurant.setCreatedBy(authenticationService.extractClaims());
            restaurant.setCuisine(cuisine);

            var rest =repository.save(restaurant);

            authenticationService.signup(restaurant,request.getSign());

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
    public RestaurantResponse Get() {

        var user = userService.userRestaurantIdService(authenticationService.extractClaims());
        var restaurant = repository.findRestaurantById(user.getRestaurant().id)
                .orElseThrow(()-> new IllegalArgumentException(""));
        return mapper.map(restaurant,RestaurantResponse.class);
    }

    @Override
    public RestaurantResponse Update(long id, RestaurantRequest request) {

        var restaurant = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No data found in id :"+id));

        mapper.map(request,restaurant);
        restaurant.setUpdatedAt(LocalDateTime.now());
        restaurant.setUpdatedBy(authenticationService.extractClaims());
         return mapper.map(repository.save(restaurant), RestaurantResponse.class);
    }

    @Override
    public RestaurantResponse ChangeStatus(long id, boolean status) {
        var restaurant = repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No data found in id "+id));


        restaurant.setOnline(status);
        restaurant.setUpdatedAt(LocalDateTime.now());
        restaurant.setUpdatedBy(authenticationService.extractClaims());

        return mapper.map(repository.save(restaurant), RestaurantResponse.class);
    }

    @Override
    public List<RestaurantResponse> getByCuisineId(long id) {
         var restaurant = repository.findByCuisineId(id)
                 .orElseThrow(()-> new IllegalArgumentException("No data found in id " + id));

        return restaurant.stream()
                .map(e-> mapper.map(e, RestaurantResponse.class))
                .toList();
    }
}
