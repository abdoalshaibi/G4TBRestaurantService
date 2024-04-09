package itep.resturant.service.service.cuisine;

import itep.resturant.service.entity.Cuisine;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.dao.request.CuisineRequestDto;
import itep.resturant.service.dao.response.CuisineResponseDto;
import itep.resturant.service.service.JwtService;
import itep.resturant.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuisineServiceImpl implements CuisineService {

    CuisineRepository repository;
    private final JwtService jwtService;
    ModelMapper mapper;

    public CuisineServiceImpl(CuisineRepository repository, JwtService jwtService, ModelMapper mapper) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.mapper = mapper;
    }


    @Override
    public CuisineResponseDto Create(CuisineRequestDto request) {
       try {


           var cuisine = mapper.map(request, Cuisine.class);
           cuisine.createdAt = LocalDateTime.now();
           cuisine.createdBy = 1;
           var rest = repository.save(cuisine);
           return mapper.map(rest, CuisineResponseDto.class);
       }catch (Exception ex)
       {
           return null;
       }
    }

    @Override
    public List<CuisineResponseDto> GetAll() {
        return repository.findAll()
                .stream()
                .map(e -> mapper.map(e, CuisineResponseDto.class))
                .toList();
    }

    @Override
    public CuisineResponseDto Update(long id, CuisineRequestDto request) {

            var restaurant = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No data found in id :" + id));

            mapper.map(request, restaurant);
            restaurant.updatedAt = LocalDateTime.now();

            return mapper.map(repository.save(restaurant), CuisineResponseDto.class);
    }
}
