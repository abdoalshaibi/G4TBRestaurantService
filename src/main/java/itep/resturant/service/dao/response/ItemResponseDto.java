package itep.resturant.service.dao.response;

import lombok.Data;

@Data
public class ItemResponseDto {
    private long id;
    private String name;
    private String image;
    private double price;
}
