package itep.resturant.service.service;

import itep.resturant.service.dao.request.ItemRequest;
import itep.resturant.service.entity.Item;
import itep.resturant.service.entity.Menu;
import itep.resturant.service.repository.ItemRepository;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.service.auth.AuthenticationService;
import itep.resturant.service.service.auth.AuthenticationServiceImpl;
import itep.resturant.service.service.item.ItemServiceImpl;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    private ItemRepository repository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ItemServiceImpl service;

    private Item item;
    private Menu menu;

    private ItemRequest ITEM_REQUEST;
    @BeforeEach
    public void setup() {

        mapper = new ModelMapper();

        repository = Mockito.mock(ItemRepository.class);
        menuRepository = Mockito.mock(MenuRepository.class);
        authenticationService=Mockito.mock(AuthenticationServiceImpl.class);

        service = new ItemServiceImpl(repository,menuRepository,authenticationService, mapper);

        menu = Menu.builder()
                .id(0)
                .name("test")
                .createdAt(LocalDateTime.now())
                .createdBy(1)
                .description(null)
                .updatedBy(0)
                .updatedAt(null)
                .build();

        item = Item.builder()
                .id(0)
                .name("test")
                .createdAt(LocalDateTime.now())
                .createdBy(1)
                .description(null)
                .updatedBy(0)
                .updatedAt(null)
                .build();


        ITEM_REQUEST = ItemRequest.builder()
                .name("test")
                .build();
    }

    @DisplayName("JUnit test for create item method")
    @Test
    void Create() {

        // Arrange
        long Id = 0L;

        when(menuRepository.findById(Id)).thenReturn(Optional.of(menu));

        when(repository.save(any())).thenReturn(item);

        //act
        var test = service.create(Id,ITEM_REQUEST);

        // Assert
        assertEquals(test.getData().getName(), ITEM_REQUEST.getName());
    }

    @DisplayName("JUnit test for update item method")
    @Test
    void Update() {

        // Arrange
        long Id = 0L;

        when(repository.findById(0L)).thenReturn(Optional.of(item));
        when(repository.save(item)).thenReturn(item);

        ITEM_REQUEST.setName("ereee");

        //act

        var test = service.update(Id,ITEM_REQUEST);

        // Assert
        assertEquals(test.getData().getName(), ITEM_REQUEST.getName());
    }
}
