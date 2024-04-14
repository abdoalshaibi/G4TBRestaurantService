package itep.resturant.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import itep.resturant.service.dao.request.ItemRequest;
import itep.resturant.service.dao.response.ItemResponse;
import itep.resturant.service.service.item.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @MockBean
    ItemServiceImpl service;

    @Autowired
    private MockMvc mockMvc;
    private ItemResponse result;
    private ItemRequest request;
    private static final ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setup() {



        request = ItemRequest.builder()
                .name("Japanese")
                .description(null)
                .build();




    }


    @DisplayName("JUnit test for Create Cuisine method")
    @Test
    void Create() throws Exception {

        long Id=0L;

        result = new ItemResponse();
        result.setName("Japanese");
        result.setPrice(1000);

        //when(service.create(Id,request)).thenReturn(result);

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/item/{id}",Id)
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
    void Update() throws Exception {

        result = new ItemResponse();
        result.setName("italian");
        result.setPrice(1000);



       // when(service.update(0L,request)).thenReturn(result);

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/item/{id}",0L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(0)))
                .andExpect(jsonPath("$.name").value("italian"));
    }
}