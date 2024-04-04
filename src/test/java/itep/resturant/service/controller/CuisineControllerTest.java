package itep.resturant.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import itep.resturant.service.service.cuisine.CuisineServiceImpl;
import itep.resturant.service.service.dto.CuisineRequestDto;
import itep.resturant.service.service.dto.CuisineResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CuisineController.class)
class CuisineControllerTest {

    @MockBean
    CuisineServiceImpl service;

    @Autowired
    private MockMvc mockMvc;
    private CuisineResponseDto result;
    private CuisineRequestDto request;
    private static final ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setup() {



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
    void testGetAll() throws Exception {

        CuisineResponseDto response = new CuisineResponseDto();
        response.setName("Japanese");
        response.setDescription(null);

        when(service.GetAll()).thenReturn(List.of(response));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cuisine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.*",hasSize(1)))
                .andExpect(jsonPath("$..id").value(0))
                .andExpect(jsonPath("$..name").value("Japanese"));
    }
}