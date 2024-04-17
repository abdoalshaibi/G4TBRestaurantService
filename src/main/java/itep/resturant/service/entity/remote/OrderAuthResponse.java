package itep.resturant.service.entity.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderAuthResponse(@JsonProperty String accessToken, @JsonProperty String refreshToken) { }
