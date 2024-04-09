package itep.resturant.service.service.cuisine;

import itep.resturant.service.dao.request.CuisineRequestDto;
import itep.resturant.service.dao.response.CuisineResponseDto;

import java.util.List;

public interface CuisineService {
    CuisineResponseDto Create(CuisineRequestDto request);
    List<CuisineResponseDto> GetAll();
    CuisineResponseDto Update(long id, CuisineRequestDto request);
}
