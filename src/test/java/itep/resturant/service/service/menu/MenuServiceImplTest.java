package itep.resturant.service.service.menu;
import itep.resturant.service.entity.Menu;
import itep.resturant.service.entity.Restaurant;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.repository.RestaurantRepository;
import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.dto.MenuResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MenuServiceImpl menuService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMenu() {
        MenuRequestDto requestDto = new MenuRequestDto();
        Menu menu = new Menu();
        MenuResponseDto responseDto = new MenuResponseDto();

        when(modelMapper.map(requestDto, Menu.class)).thenReturn(menu);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(new Restaurant()));
        when(menuRepository.save(menu)).thenReturn(menu);
        when(modelMapper.map(menu, MenuResponseDto.class)).thenReturn(responseDto);

        MenuResponseDto result = menuService.Create(1L, requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    public void testGetMenuById() {
        Menu menu = new Menu();
        MenuResponseDto responseDto = new MenuResponseDto();

        when(menuRepository.findAllByRestaurantId(1L)).thenReturn(Arrays.asList(menu));
        when(modelMapper.map(menu, MenuResponseDto.class)).thenReturn(responseDto);

        List<MenuResponseDto> result = menuService.GetById(1L);

        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
    }

    @Test
    public void testUpdateMenu() {
        MenuRequestDto requestDto = new MenuRequestDto();
        Menu menu = new Menu();
        MenuResponseDto responseDto = new MenuResponseDto();

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
        when(menuRepository.save(menu)).thenReturn(menu);
        when(modelMapper.map(menu, MenuResponseDto.class)).thenReturn(responseDto);

        MenuResponseDto result = menuService.Update(1L, requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    public void testDeleteMenu() {
        Menu menu = new Menu();

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        menuService.Delete(1L);

        Mockito.verify(menuRepository).delete(menu);
    }
}
