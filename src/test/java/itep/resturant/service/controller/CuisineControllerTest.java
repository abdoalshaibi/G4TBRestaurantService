package itep.resturant.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import itep.resturant.service.entity.Cuisine;
import itep.resturant.service.repository.CuisineRepository;
import itep.resturant.service.service.cuisine.CuisineServiceImpl;
import itep.resturant.service.service.dto.CuisineRequestDto;
import itep.resturant.service.service.dto.CuisineResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CuisineController.class)
class CuisineControllerTest {

    @MockBean
    CuisineServiceImpl service;

    @Mock
    CuisineRepository repository;
    @Autowired
    private MockMvc mockMvc;
    private CuisineResponseDto result;
    private Cuisine cuisine;
    private CuisineRequestDto request;
    private static final ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setup() {

    cuisine  = Cuisine.builder()
        .name("Japanese")
        .description(null)
        .build();

        request = CuisineRequestDto.builder()
                .name("Japanese")
                .description(null)
                .build();




    }


    @DisplayName("JUnit test for Create Cuisine method")
    @Test
    void testCreate() throws Exception {

        result = new CuisineResponseDto();
        result.setName("Japanese");
        result.setDescription(null);

        when(service.Create(any())).thenReturn(result);

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cuisine")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(0)))
                .andExpect(jsonPath("$.name",equalTo("Japanese")));
    }

    @DisplayName("JUnit test for Update Cuisine method")
    @Test
    void testUpdate() throws Exception {


        result = new CuisineResponseDto();
        result.setName("italian");
        result.setDescription(null);

        request.setName("italian");

        when(service.Update(0L,request)).thenReturn(result);



        String json = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/cuisine/{id}",0L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(0)))
                .andExpect(jsonPath("$.name",equalTo("italian")));
    }


    @DisplayName("JUnit test for Get All Cuisine method")
    @Test
    void testGetAll() {


    }
}