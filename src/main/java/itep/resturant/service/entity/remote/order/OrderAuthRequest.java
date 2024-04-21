package itep.resturant.service.entity.remote.order;

import com.fasterxml.jackson.annotation.JsonProperty;


public record OrderAuthRequest (@JsonProperty String email, @JsonProperty String password)
{
}
