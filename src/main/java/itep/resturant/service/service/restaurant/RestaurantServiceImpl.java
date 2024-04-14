package itep.resturant.service.service.restaurant;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.dao.request.RestaurantRequest;
import itep.resturant.service.dao.response.RestaurantResponse;
import itep.resturant.service.service.auth.AuthenticationService;
import itep.resturant.service.service.auth.UserService;
import itep.resturant.service.util.Constant;
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
    public APIResponse<RestaurantResponse> Create(long id, RestaurantRequest request) {

        var cuisineOptional = cuisineRepository.findById(id);

        if (cuisineOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(), "CUISINE-".concat("5"));

        var restaurant = mapper.map(request, Restaurant.class);

        restaurant.setCreatedAt(LocalDateTime.now());
        restaurant.setCreatedBy(authenticationService.extractClaims());
        restaurant.setCuisine(cuisineOptional.get());

        authenticationService.signup(restaurant, request.getSign());

        var response = mapper.map(repository.save(restaurant), RestaurantResponse.class);

        return APIResponse.ok(response, Constant.getLogResponseHashMap(), "RESTAURANT-".concat("7"));
    }


    @Override
    public APIResponse<List<RestaurantResponse>> GetAll() {

        var restaurants =  repository.findAll()
                .stream()
                .map(e->mapper.map(e, RestaurantResponse.class))
                .toList();

        return APIResponse.ok(restaurants,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("10"));
    }

    @Override
    public APIResponse<RestaurantResponse> Get() {

        var user = userService.userRestaurantIdService(authenticationService.extractClaims());

        var restaurant = repository.findRestaurantById(user.getRestaurant().id)
                .orElseThrow(()-> new IllegalArgumentException(""));

        var response = mapper.map(restaurant,RestaurantResponse.class);

        return APIResponse.ok(response,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("10"));

    }

    @Override
    public APIResponse<RestaurantResponse> Update(long id, RestaurantRequest request) {

        var restaurantOptional = repository.findById(id);

        if (restaurantOptional.isEmpty())
            return APIResponse.notFound(null,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("5"));

        var restaurant = restaurantOptional.get();

        mapper.map(request,restaurant);
        restaurant.setUpdatedAt(LocalDateTime.now());
        restaurant.setUpdatedBy(authenticationService.extractClaims());

         var response = mapper.map(repository.save(restaurant), RestaurantResponse.class);

        return APIResponse.ok(response,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("8"));

    }

    @Override
    public APIResponse<RestaurantResponse> ChangeStatus(long id, boolean status) {
        var restaurantOptional = repository.findById(id);

        if (restaurantOptional.isEmpty())
            return APIResponse.notFound(null,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("5"));

        var restaurant = restaurantOptional.get();

        restaurant.setOnline(status);
        restaurant.setUpdatedAt(LocalDateTime.now());
        restaurant.setUpdatedBy(authenticationService.extractClaims());

        var response = mapper.map(repository.save(restaurant), RestaurantResponse.class);

        return APIResponse.ok(response,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("13"));

    }

    @Override
    public APIResponse<List<RestaurantResponse>> getByCuisineId(long id) {
         var restaurantOptional = repository.findByCuisineId(id);

        if (restaurantOptional.isEmpty())
            return APIResponse.notFound(null,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("5"));

        var restaurant = restaurantOptional.get();

        var response = restaurant.stream()
                .map(e-> mapper.map(e, RestaurantResponse.class))
                .toList();

        return APIResponse.ok(response,Constant.getLogResponseHashMap(),"RESTAURANT-".concat("10"));

    }
}
