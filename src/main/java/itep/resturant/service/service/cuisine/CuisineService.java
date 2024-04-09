package itep.resturant.service.service.cuisine;

import itep.resturant.service.dao.request.CuisineRequest;
import itep.resturant.service.dao.response.CuisineResponse;

import java.util.List;

public interface CuisineService {
    CuisineResponse Create(CuisineRequest request);
    List<CuisineResponse> GetAll();
    CuisineResponse Update(long id, CuisineRequest request);
}
