//package itep.resturant.service.service;
//
//import itep.resturant.service.dao.request.CuisineRequest;
//import itep.resturant.service.entity.local.Cuisine;
//import itep.resturant.service.repository.local.CuisineRepository;
//import itep.resturant.service.service.auth.AuthenticationService;
//import itep.resturant.service.service.cuisine.CuisineServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//@ExtendWith(MockitoExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class CuisineServiceImplTest {
//
//    @InjectMocks
//    CuisineServiceImpl service;
//    @Mock
//    CuisineRepository repository;
//    @Mock
//    AuthenticationService authenticationService;
//    @Mock
//    private ModelMapper mapper;
//    private Cuisine cuisine;
//    private CuisineRequest CUISINE_REQUEST;
//
//    @BeforeEach
//    public void setup() {
//
//        mapper = new ModelMapper();
//
//        repository = Mockito.mock(CuisineRepository.class);
//        authenticationService = Mockito.mock(AuthenticationService.class);
//        service = new CuisineServiceImpl(repository,mapper,authenticationService);
//
//        cuisine = Cuisine.builder()
//                .id(0)
//                .name("Japanese")
//                .createdAt(LocalDateTime.now())
//                .createdBy(1)
//                .description(null)
//                .updatedBy(0)
//                .updatedAt(null)
//                .restaurants(null)
//                .build();
//
//
//        CUISINE_REQUEST = CuisineRequest.builder()
//                .name("Japanese")
//                .build();
//    }
//
//
//    @DisplayName("JUnit test for Create Cuisine method")
//    @Test
//    void Create() {
//
//        when(repository.save(any())).thenReturn(cuisine);
//
//        var test = service.Create(CUISINE_REQUEST);
//
//        assertEquals(test.getData().getName(), CUISINE_REQUEST.getName());
//
//    }
//
//    @DisplayName("JUnit test for Update Cuisine method")
//    @Test
//    void Update() {
//
//        long Id = 0L;
//
//        when(repository.findById(0L)).thenReturn(Optional.of(cuisine));
//        when(repository.save(cuisine)).thenReturn(cuisine);
//
//        CUISINE_REQUEST.setName("Italian");
//
//        var test = service.Update(Id,CUISINE_REQUEST);
//
//        assertEquals(test.getData().getName(), CUISINE_REQUEST.getName());
//
//    }
//
//
//    @DisplayName("JUnit test for Get All Cuisine method")
//    @Test
//    void GetAll() {
//
//        when(repository.findAll()).thenReturn(List.of(cuisine));
//
//
//        var test = service.GetAll();
//
//        assertThat(test.getData().size()).isEqualTo(1);
//
//    }
//}
