package itep.resturant.service.service;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDto {

    private String name;
    private String location;
    private int phone;
    private String latitude;
    private String longitude;
    private boolean isOnline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

 
}
