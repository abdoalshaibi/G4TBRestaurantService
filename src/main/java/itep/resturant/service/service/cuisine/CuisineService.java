package itep.resturant.service.service.cuisine;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.dao.request.CuisineRequest;
import itep.resturant.service.dao.response.CuisineResponse;

import java.util.List;

public interface CuisineService {
    APIResponse<CuisineResponse> Create(CuisineRequest request);
    APIResponse<List<CuisineResponse>> GetAll();
    APIResponse<CuisineResponse> Update(long id, CuisineRequest request);
}
