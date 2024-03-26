package itep.resturant.service.service.item;
import itep.resturant.service.entity.Item;
import itep.resturant.service.repository.ItemRepository;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.service.dto.ItemRequestDto;
import itep.resturant.service.service.dto.ItemResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ItemServiceImpl class.
 */
class ItemServiceImplTest {

    @Mock
    private ItemRepository repository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test the Create method of the ItemServiceImpl class.
     */
    @Test
    void testCreate() {
        // Arrange
        long menuId = 1L;
        ItemRequestDto requestDto = new ItemRequestDto();
        Item item = new Item();
        ItemResponseDto responseDto = new ItemResponseDto();

        when(mapper.map(requestDto, Item.class)).thenReturn(item);
        when(menuRepository.findById(menuId)).thenReturn(Optional.of(new itep.resturant.service.entity.Menu()));
        when(repository.save(item)).thenReturn(item);
        when(mapper.map(item, ItemResponseDto.class)).thenReturn(responseDto);

        // Act
        ItemResponseDto result = itemService.Create(menuId, requestDto);

        // Assert
        assertEquals(responseDto, result);
        verify(mapper, times(1)).map(requestDto, Item.class);
        verify(menuRepository, times(1)).findById(menuId);
        verify(repository, times(1)).save(item);
        verify(mapper, times(1)).map(item, ItemResponseDto.class);
    }

    /**
     * Test the Update method of the ItemServiceImpl class.
     */
    @Test
    void testUpdate() {
        // Arrange
        long itemId = 1L;
        ItemRequestDto requestDto = new ItemRequestDto();
        Item item = new Item();
        ItemResponseDto responseDto = new ItemResponseDto();

        when(repository.findById(itemId)).thenReturn(Optional.of(item));
          when(repository.save(item)).thenReturn(item);
        when(mapper.map(item, ItemResponseDto.class)).thenReturn(responseDto);

        // Act
        ItemResponseDto result = itemService.Update(itemId, requestDto);

        // Assert
        assertEquals(responseDto, result);
        verify(repository, times(1)).findById(itemId);
        verify(mapper, times(1)).map(requestDto, item);
        verify(repository, times(1)).save(item);
        verify(mapper, times(1)).map(item, ItemResponseDto.class);
    }
}
