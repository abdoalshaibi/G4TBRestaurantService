package itep.resturant.service.service.restaurant;

import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRestaurant() {
        RestaurantRequestDto request = new RestaurantRequestDto();
        Restaurant restaurant = new Restaurant();
        RestaurantResponseDto responseDto = new RestaurantResponseDto();

        when(mapper.map(request, Restaurant.class)).thenReturn(restaurant);
        when(repository.save(restaurant)).thenReturn(restaurant);
        when(mapper.map(restaurant, RestaurantResponseDto.class)).thenReturn(responseDto);

        RestaurantResponseDto result = restaurantService.Create(request);

        verify(repository, times(1)).save(restaurant);
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> restaurants = Collections.singletonList(new Restaurant());
        List<RestaurantResponseDto> responseDtos = Collections.singletonList(new RestaurantResponseDto());

        when(repository.findAll()).thenReturn(restaurants);
        when(mapper.map(any(), eq(RestaurantResponseDto.class))).thenReturn(responseDtos.get(0));

        List<RestaurantResponseDto> result = restaurantService.GetAll();

        assertEquals(responseDtos, result);
    }

    @Test
    public void testUpdateRestaurant() {
        long id = 1L;
        RestaurantRequestDto request = new RestaurantRequestDto();
        Restaurant restaurant = new Restaurant();
        RestaurantResponseDto responseDto = new RestaurantResponseDto();

        when(repository.findById(id)).thenReturn(Optional.of(restaurant));
        when(repository.save(restaurant)).thenReturn(restaurant);
        when(mapper.map(restaurant, RestaurantResponseDto.class)).thenReturn(responseDto);

        RestaurantResponseDto result = restaurantService.Update(id, request);

        verify(repository, times(1)).save(restaurant);
        assertEquals(responseDto, result);
    }

    @Test
    public void testChangeStatus() {
        long id = 1L;
        boolean status = true;
        Restaurant restaurant = new Restaurant();
        RestaurantResponseDto responseDto = new RestaurantResponseDto();

        when(repository.findById(id)).thenReturn(Optional.of(restaurant));
        when(repository.save(restaurant)).thenReturn(restaurant);
        when(mapper.map(restaurant, RestaurantResponseDto.class)).thenReturn(responseDto);

        RestaurantResponseDto result = restaurantService.ChangeStatus(id, status);

        verify(repository, times(1)).save(restaurant);
        assertEquals(responseDto, result);
    }
}
