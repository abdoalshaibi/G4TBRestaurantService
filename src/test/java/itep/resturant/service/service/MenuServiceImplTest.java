package itep.resturant.service.service;

import itep.resturant.service.dao.request.MenuRequest;
import itep.resturant.service.entity.local.Menu;
import itep.resturant.service.entity.local.Restaurant;
import itep.resturant.service.repository.local.MenuRepository;
import itep.resturant.service.repository.local.RestaurantRepository;
import itep.resturant.service.service.auth.AuthenticationService;
import itep.resturant.service.service.menu.MenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    private  AuthenticationService authenticationService;
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private MenuServiceImpl service;

    private Restaurant restaurant;
    private Menu menu;

    private MenuRequest MENU_REQUEST;
    @BeforeEach
    public void setup() {

        mapper = new ModelMapper();

        repository = Mockito.mock(RestaurantRepository.class);
        menuRepository = Mockito.mock(MenuRepository.class);
        authenticationService = Mockito.mock(AuthenticationService.class);


        service = new MenuServiceImpl(menuRepository,repository,authenticationService, mapper);

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




        MENU_REQUEST = MenuRequest.builder()
                .name("pizzeria")
                .build();
    }

    @DisplayName("JUnit test for create menu method")
    @Test
    void Create() {

        // Arrange
        long Id = 0L;

        when(repository.findById(Id)).thenReturn(Optional.of(restaurant));

        when(menuRepository.save(any())).thenReturn(menu);

        //act
        var test = service.create(Id,MENU_REQUEST);

        // Assert
        assertEquals(test.getData().getName(), MENU_REQUEST.getName());
    }

    @DisplayName("JUnit test for get menu by id method")
    @Test
    void GetById() {

        // Arrange
        long Id = 0L;

        when(menuRepository.findAllByRestaurantId(Id)).thenReturn(List.of(menu));

        //act

        var test = service.getAllById(Id);

        // Assert

        assertThat(test.getData().size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for update menu method")
    @Test
    void Update() {

        // Arrange
        long Id = 0L;

        when(menuRepository.findById(0L)).thenReturn(Optional.of(menu));
        when(menuRepository.save(any())).thenReturn(menu);

        MENU_REQUEST.setName("ereee");

        //act

        var test = service.update(Id,MENU_REQUEST);

        // Assert
        assertEquals(test.getData().getName(), MENU_REQUEST.getName());
    }


}
