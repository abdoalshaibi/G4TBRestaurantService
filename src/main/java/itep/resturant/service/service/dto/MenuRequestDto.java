package itep.resturant.service.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MenuRequestDto {
    @NotBlank(message = "Name is mandatory")
    @NotNull
    @Size(min=2, max=30)
    private String name;
    private String image;
    private String description;
}
