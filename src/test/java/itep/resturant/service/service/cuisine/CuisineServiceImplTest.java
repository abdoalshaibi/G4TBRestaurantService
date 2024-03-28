package itep.resturant.service.service.cuisine;

import itep.resturant.service.entity.Cuisine;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.service.dto.CuisineRequestDto;
import itep.resturant.service.service.dto.CuisineResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuisineServiceImplTest {

    @InjectMocks
    CuisineServiceImpl service;
    @Autowired
    CuisineRepository repository;
    @Mock
    private ModelMapper mapper;
    private Cuisine cuisine;
    private CuisineRequestDto CUISINE_REQUEST;

    @BeforeEach
    public void setup() {

        mapper = new ModelMapper();

        repository = Mockito.mock(CuisineRepository.class);
        service = new CuisineServiceImpl(repository, mapper);

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


        CUISINE_REQUEST = CuisineRequestDto.builder()
                .name("test")
                .build();
    }


    @DisplayName("JUnit test for CreateCuisine method")
    @Test
    void testCreate() {


        when(repository.save(any())).thenReturn(cuisine);

        CuisineResponseDto test = service.Create(CUISINE_REQUEST);

        assertEquals(test.getName(), CUISINE_REQUEST.getName());

    }
}
