package itep.resturant.service.service.dto;

import lombok.Data;

@Data
public class ItemResponseDto {
    private long id;
    private String name;
    private String image;
    private double price;
}
