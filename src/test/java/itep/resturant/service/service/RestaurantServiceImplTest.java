package itep.resturant.service.service;

import itep.resturant.service.dao.request.RestaurantRequest;
import itep.resturant.service.entity.Cuisine;
import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.restaurant.RestaurantServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    @Mock
    CuisineRepository cuisineRepository;
    @Mock
    RestaurantRepository restaurantRepository;
    @InjectMocks
    private RestaurantServiceImpl service;
    @Mock
    ModelMapper mapper;

    private static  Restaurant restaurant ;
    private Cuisine cuisine;
    private static RestaurantRequest request;


    @BeforeEach
    public void setup() {

        this.mapper = new ModelMapper();

        cuisineRepository = mock(CuisineRepository.class);
        restaurantRepository =mock(RestaurantRepository.class);

        //service = new RestaurantServiceImpl(restaurantRepository,cuisineRepository,mapper);

        cuisine = Cuisine.builder()
                .id(0)
                .name("test")
                .createdAt(LocalDateTime.now())
                .createdBy(1)
                .description(null)
                .updatedBy(0)
                .updatedAt(null)
                .restaurants(null)
                .build();

        restaurant = Restaurant.builder()
                .name("Ballhaus Watzke")
                .location("Dresden")
                .phone("0351852920")
                .email("verkauf@watzke.de")
                .cuisine(cuisine)
                .latitude("31.5048719")
                .longitude("47.1257121")
                .isOnline(true)
                .build();

        request = RestaurantRequest.builder()
                .name("Ballhaus Watzke")
                .phone("0351852920")
                .email("verkauf@watzke.de")
                .build();


    }

    @DisplayName("JUnit test for create restaurant method")
    @Test
    public void Create() {

        long id = 0L;
        when(cuisineRepository.findById(id)).thenReturn(Optional.of(cuisine));
        when(restaurantRepository.save(any())).thenReturn(restaurant);

        var test = service.Create(id,request);

        assertThat(test.name).isEqualTo(request.getName());
    }

    @DisplayName("JUnit test for update restaurant method")
    @Test
    void Update() {

        // Arrange
        long Id = 0L;

        when(restaurantRepository.findById(0L)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        request.setName("Zur Letzten Instanz");

        //act
        var test = service.Update(Id,request);

        // Assert
        assertEquals(test.getName(), request.getName());
    }
    @DisplayName("JUnit test for Get All restaurant method")
    @Test
    void testGetAll() {

        when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));


        var test = service.GetAll();

        Assertions.assertThat(test.size()).isEqualTo(1);

    }

    @DisplayName("JUnit test for Get All restaurant method")
    @Test
    void GetByCuisine() {

        long id = 0L;

        when(restaurantRepository.findByCuisineId(id)).thenReturn((Optional.of(List.of(restaurant))));


        var test = service.getByCuisineId(0L);

        Assertions.assertThat(test.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for change status restaurant method")
    @Test
    void ChangeStatus() {

        // Arrange
        long Id = 0L;

        when(restaurantRepository.findById(0L)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        //act
        var test = service.ChangeStatus(Id,false);

        // Assert
        assertFalse(test.isOnline());
    }
}
