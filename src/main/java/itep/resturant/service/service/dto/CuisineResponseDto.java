package itep.resturant.service.service.dto;

import lombok.Builder;
import lombok.Data;

@Data

public class CuisineResponseDto {
    public long id;
    public String name;
    public String description;
}
