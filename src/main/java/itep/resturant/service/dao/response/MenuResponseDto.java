package itep.resturant.service.dao.response;

import lombok.Data;

@Data
public class MenuResponseDto {
    private long id;
    private String name;
    private String image;
    private String description;
}
