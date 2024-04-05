package itep.resturant.service.service.dto;

import lombok.Data;

@Data
public class MenuResponseDto {
    private long id;
    private String name;
    private String image;
    private String description;
}
