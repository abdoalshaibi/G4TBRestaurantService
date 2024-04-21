package itep.resturant.service.entity.remote.order;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public record OrderResponse(
    @JsonProperty ArrayList<Object> orders,
    @JsonProperty int pageNo,
    @JsonProperty int pageSize,
    @JsonProperty int totalElements,
    @JsonProperty int totalPages,
    @JsonProperty boolean last){ }


