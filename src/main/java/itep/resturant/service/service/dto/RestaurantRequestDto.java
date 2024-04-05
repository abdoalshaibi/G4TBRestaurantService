package itep.resturant.service.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

@Data
@Builder
public class  RestaurantRequestDto {

    @NotBlank(message = "name is required")
    @NotNull(message ="name is required" )
    @Size(min = 5, max = 50, message = "The field must be between {min} and {max} characters long")
    public String name;
    @NotBlank(message = "Location is required")
    @NotNull(message ="Location is required" )
    public String location;
    public MultipartFile image;
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    public String email;
    public String phone;
    public int mobile;
    @NotBlank(message = "Latitude is required")
    @NotNull(message ="Latitude is required" )
    public String latitude;
    @NotBlank(message = "Longitude is required")
    @NotNull(message ="Longitude is required" )
    public String longitude;
    public boolean isOnline;
    @Size(min = 150, max = 2000, message = "The field must be between {min} and {max} characters long")
    private String description;
    //@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid DateTime format. Expected format: yyyy-MM-dd HH:mm:ss")
    @NotNull(message ="OpeningAt is required" )
    public LocalTime openingAt;
    @NotNull(message ="ClosingAt is required" )
    public LocalTime closingAt;

}
