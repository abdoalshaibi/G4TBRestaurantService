package itep.resturant.service.service.menu;

import itep.resturant.service.entity.Menu;
import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.MenuRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuServiceImplTest {

    @Mock
    private RestaurantRepository repository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private MenuServiceImpl service;

    private Restaurant restaurant;
    private Menu menu;

    private MenuRequestDto MENU_REQUEST;
    @BeforeEach
    public void setup() {

        mapper = new ModelMapper();

        repository = Mockito.mock(RestaurantRepository.class);
        menuRepository = Mockito.mock(MenuRepository.class);


        service = new MenuServiceImpl(menuRepository,repository, mapper);

        restaurant = Restaurant.builder()
                .id(0)
                .name("Ballhaus Watzke")
                .createdAt(LocalDateTime.now())
                .createdBy(1)
                .description(null)
                .updatedBy(0)
                .updatedAt(null)
                .build();

        menu = Menu.builder()
                .id(0)
                .name("pizzeria")
                .createdAt(LocalDateTime.now())
                .createdBy(1)
                .description(null)
                .updatedBy(0)
                .updatedAt(null)
                .build();




        MENU_REQUEST = MenuRequestDto.builder()
                .name("pizzeria")
                .build();
    }
    /**
     * Test the Create method of the ItemServiceImpl class.
     */
    @Test
    void testCreate() {

        // Arrange
        long Id = 0L;

        when(repository.findById(Id)).thenReturn(Optional.of(restaurant));

        when(menuRepository.save(any())).thenReturn(menu);

        //act
        var test = service.Create(Id,MENU_REQUEST);

        // Assert
        assertEquals(test.getName(), MENU_REQUEST.getName());
    }

    @Test
    void testGetById() {

        // Arrange
        long Id = 0L;

        when(menuRepository.findAllByRestaurantId(Id)).thenReturn(List.of(menu));

        //act

        var test = service.GetById(Id);

        // Assert

        assertThat(test.size()).isEqualTo(1);
    }

    @Test
    void testUpdate() {

        // Arrange
        long Id = 0L;

        when(menuRepository.findById(0L)).thenReturn(Optional.of(menu));
        when(menuRepository.save(any())).thenReturn(menu);

        MENU_REQUEST.setName("ereee");

        //act

        var test = service.Update(Id,MENU_REQUEST);

        // Assert
        assertEquals(test.getName(), MENU_REQUEST.getName());
    }

//    @Test
//    void testDelete() {
//
//        // Arrange
//        long Id = 0L;
//
//        when(menuRepository.findAllByRestaurantId(Id)).thenReturn(List.of(menu));
//
//        //act
//
//        var test = service.Delete(Id);
//
//        // Assert
//
//        assertThat(test.size()).isEqualTo(1);
//    }
}
