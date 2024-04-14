package itep.resturant.service.service.cuisine;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.dao.request.CuisineRequest;
import itep.resturant.service.dao.response.CuisineResponse;
import itep.resturant.service.dao.response.ItemResponse;
import itep.resturant.service.entity.Cuisine;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.service.auth.AuthenticationService;
import itep.resturant.service.util.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuisineServiceImpl implements CuisineService {

    private final CuisineRepository repository;
    private final ModelMapper mapper;
    private final AuthenticationService authenticationService;

    public CuisineServiceImpl(CuisineRepository repository, ModelMapper mapper, AuthenticationService authenticationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.authenticationService = authenticationService;
    }


    @Override
    public APIResponse<CuisineResponse> Create(CuisineRequest request) {

           var cuisine = mapper.map(request, Cuisine.class);
           cuisine.createdAt = LocalDateTime.now();
           cuisine.createdBy = authenticationService.extractClaims();
           var rest = repository.save(cuisine);

        return APIResponse.ok(mapper.map(rest, CuisineResponse.class), Constant.getLogResponseHashMap(), "CUISINE-".concat("3"));


    }

    @Override
    public APIResponse<List<CuisineResponse>> GetAll() {
        var cuisines = repository.findAll()
                .stream()
                .map(e -> mapper.map(e, CuisineResponse.class))
                .toList();

        return APIResponse.ok(cuisines, Constant.getLogResponseHashMap(), "CUISINE-".concat("3"));

    }

    @Override
    public APIResponse<CuisineResponse> Update(long id, CuisineRequest request) {

        var restaurantOptional = repository.findById(id);

        if (restaurantOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(), "ITEM-".concat("1"));

        var restaurant = restaurantOptional.get();
        mapper.map(request, restaurant);
        restaurant.setUpdatedAt(LocalDateTime.now());
        restaurant.setUpdatedBy(authenticationService.extractClaims());

        return APIResponse.ok(mapper.map(repository.save(restaurant), CuisineResponse.class), Constant.getLogResponseHashMap(), "CUISINE-".concat("3"));

    }
}
