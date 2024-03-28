package itep.resturant.service.service.restaurant;

import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    private static final long cuisine = 1;
    @Mock
    CuisineRepository cuisineRepository;
    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private static final RestaurantRequestDto REQUEST = RestaurantRequestDto.builder()
            .name("AL-Shuaibni")
            .location("hadah")
            .phone(888)
            .latitude("jjjj")
            .latitude("kkkk")
            .isOnline(true)
            .build();


    @Test
    public void testCreateRestaurant() {

         RestaurantResponseDto RESPONSE = RestaurantResponseDto.builder()
                 .Id(1)
                .name("AL-Shuaibni")
                .location("hadah")
                .phone(888)
                .latitude("jjjj")
                .latitude("kkkk")
                .isOnline(true)
                .build();

        when(restaurantService.Create(cuisine,REQUEST)).thenReturn(RESPONSE);

        assertThat(REQUEST.name).isEqualTo(RESPONSE.getName());
    }

}
