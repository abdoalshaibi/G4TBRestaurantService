package itep.resturant.service.service.cuisine;

import itep.resturant.service.entity.Cuisine;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.dao.request.CuisineRequest;
import itep.resturant.service.dao.response.CuisineResponse;
import itep.resturant.service.service.auth.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuisineServiceImpl implements CuisineService {

    CuisineRepository repository;
    ModelMapper mapper;

    public CuisineServiceImpl(CuisineRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CuisineResponse Create(CuisineRequest request) {
       try {


           var cuisine = mapper.map(request, Cuisine.class);
           cuisine.createdAt = LocalDateTime.now();
           cuisine.createdBy = 1;
           var rest = repository.save(cuisine);
           return mapper.map(rest, CuisineResponse.class);
       }catch (Exception ex)
       {
           return null;
       }
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
            restaurant.updatedAt = LocalDateTime.now();

            return mapper.map(repository.save(restaurant), CuisineResponse.class);
    }
}
