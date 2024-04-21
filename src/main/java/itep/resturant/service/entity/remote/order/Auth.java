package itep.resturant.service.entity.remote.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Auth(@JsonProperty String access_token, @JsonProperty String refresh_token){ }
