package itep.resturant.service.service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class  RestaurantRequestDto {

    @NotBlank(message = "name is required")
   public String name;
    public String location;
    public MultipartFile image;
    public String email;
    public int phone;
    public String latitude;
    public String longitude;
    public boolean isOnline;
}
