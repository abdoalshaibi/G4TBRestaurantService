//package itep.resturant.service.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import itep.resturant.service.dao.request.RestaurantRequest;
//import itep.resturant.service.dao.response.RestaurantResponse;
//import itep.resturant.service.service.restaurant.RestaurantServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.time.LocalTime;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(RestaurantController.class)
//class RestaurantControllerTest {
//
//    @MockBean
//    RestaurantServiceImpl service;
//
//    @Autowired
//    private MockMvc mockMvc;
//    private RestaurantResponse result;
//    private RestaurantRequest request;
//    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
//
//    @BeforeEach
//    public void setup() {
//
//        request = RestaurantRequest.builder()
//                .name("Ballhaus Watzke")
//                .description(null)
//                .location("Dresden")
//                .email("verkauf@watzke.de")
//                .longitude("31.5048719")
//                .latitude("47.1257121")
//                .closingAt(LocalTime.now())
//                .openingAt(LocalTime.now())
//                .build();
//    }
//
//
//    @DisplayName("JUnit test for Create Cuisine method")
//    @Test
//    void Create() throws Exception {
//
//        long Id=0L;
//
//        result = new RestaurantResponse();
//        result.setName("Ballhaus Watzke");
//
//        //when(service.Create(Id,request)).thenReturn(result);
//
//        String json = mapper.writeValueAsString(request);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurant/{id}",Id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//                        .content(json)
//                        .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", equalTo(0)))
//                .andExpect(jsonPath("$.name",equalTo("Ballhaus Watzke")));
//    }
//
//    @DisplayName("JUnit test for Update Cuisine method")
//    @Test
//    void Update() throws Exception {
//
//        result = new RestaurantResponse();
//        result.setName("Zur Letzten Instanz");
//
//
//        //when(service.Update(0L,request)).thenReturn(result);
//
//        String json = mapper.writeValueAsString(request);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/{id}",0L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//                        .content(json)
//                        .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id",equalTo(0)))
//                .andExpect(jsonPath("$.name").value("Zur Letzten Instanz"));
//    }
//}