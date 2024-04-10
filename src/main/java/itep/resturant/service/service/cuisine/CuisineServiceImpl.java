package itep.resturant.service.service.cuisine;

import itep.resturant.service.dao.request.CuisineRequest;
import itep.resturant.service.dao.response.CuisineResponse;
import itep.resturant.service.entity.Cuisine;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.service.auth.AuthenticationService;
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
    public CuisineResponse Create(CuisineRequest request) {

           var cuisine = mapper.map(request, Cuisine.class);
           cuisine.createdAt = LocalDateTime.now();
           cuisine.createdBy = authenticationService.extractClaims();
           var rest = repository.save(cuisine);
           return mapper.map(rest, CuisineResponse.class);

    }

    @Override
    public List<CuisineResponse> GetAll() {
        return repository.findAll()
                .stream()
                .map(e -> mapper.map(e, CuisineResponse.class))
                .toList();
    }

    @Override
    public CuisineResponse Update(long id, CuisineRequest request) {

            var restaurant = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No data found in id :" + id));

            mapper.map(request, restaurant);
            restaurant.setUpdatedAt(LocalDateTime.now());
            restaurant.setUpdatedBy(authenticationService.extractClaims());
            return mapper.map(repository.save(restaurant), CuisineResponse.class);
    }
}
