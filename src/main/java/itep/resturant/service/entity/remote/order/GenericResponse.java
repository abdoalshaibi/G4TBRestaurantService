package itep.resturant.service.entity.remote.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GenericResponse<T>(@JsonProperty long timestamp,
                                 @JsonProperty int status,
                                 @JsonProperty String message,
                                 @JsonProperty T data) { }
