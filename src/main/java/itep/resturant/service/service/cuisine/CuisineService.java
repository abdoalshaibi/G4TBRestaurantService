package itep.resturant.service.service.cuisine;

import java.util.List;

public interface CuisineService {
    CuisineResponseDto Create(CuisineRequestDto request);
    List<CuisineResponseDto> GetAll();
    CuisineResponseDto Update(long id, CuisineRequestDto request);
}
