package itep.resturant.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.service.cuisine.CuisineServiceImpl;
import itep.resturant.service.dao.request.CuisineRequest;
import itep.resturant.service.dao.response.CuisineResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuisineController.class)
class CuisineControllerTest {

    @MockBean
    CuisineServiceImpl service;

    @Autowired
    private MockMvc mockMvc;
    private APIResponse<CuisineResponse> Apiresult;

    private CuisineRequest request;
    private static final ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setup() {



        request = CuisineRequest.builder()
                .name("Japanese")
                .description(null)
                .build();

    }


    @DisplayName("JUnit test for Create Cuisine method")
    @Test
    void testCreate() throws Exception {

        CuisineResponse result = new CuisineResponse();
        request.setName("");
        result.setName("Japanese");
        result.setDescription(null);

        Apiresult.setData(result);

        when(service.Create(any())).thenReturn(Apiresult);

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cuisine")

                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .with(jwt().authorities(new SimpleGrantedAuthority("ADMIN")))).
        andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(0)))
                .andExpect(jsonPath("$.name",equalTo("Japanese")));
    }

//    @DisplayName("JUnit test for Update Cuisine method")
//    @Test
//    void Update() throws Exception {
//
//
//        result = new CuisineResponse();
//        result.setName("italian");
//        result.setDescription(null);
//
//
//        when(service.Update(0L,request)).thenReturn(result);
//
//
//        String json = mapper.writeValueAsString(request);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/cuisine/{id}",0L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//                        .content(json)
//                        .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id",equalTo(0)))
//                .andExpect(jsonPath("$.name",equalTo("italian")));
//    }
//
//
//    @DisplayName("JUnit test for Get All Cuisine method")
//    @Test
//    void GetAll() throws Exception {
//
//        CuisineResponse response = new CuisineResponse();
//        response.setName("Japanese");
//        response.setDescription(null);
//
//        when(service.GetAll()).thenReturn(List.of(response));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cuisine")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//                        .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andExpect(jsonPath("$.*",hasSize(1)))
//                .andExpect(jsonPath("$..id").value(0))
//                .andExpect(jsonPath("$..name").value("Japanese"));
//    }
}