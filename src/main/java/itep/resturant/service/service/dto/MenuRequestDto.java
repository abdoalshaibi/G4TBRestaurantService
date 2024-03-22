package itep.resturant.service.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuRequestDto {
    @NotBlank(message = "name is required")
    @NotNull(message ="name is required" )
    @Size(min = 5, max = 50, message = "The field must be between {min} and {max} characters long")
    private String name;
    private String  image;
    @Size(min = 150, max = 2000, message = "The field must be between {min} and {max} characters long")
    private String description;
}
