package itep.resturant.service.controller;

import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.dto.MenuResponseDto;
import itep.resturant.service.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menu")
public class MenuController {

    @Autowired
    MenuService service;

    @PostMapping("/{restaurant_id}")
    public MenuResponseDto Create(@PathVariable long restaurant_id, MenuRequestDto request)
    {
         return service.Create(restaurant_id,request);
    }
    @GetMapping("/{restaurant_id}")
    public List<MenuResponseDto> getAll(@PathVariable long restaurant_id)
    {
        return service.GetById(restaurant_id);
    }

    @PutMapping("/{id}")
    public MenuResponseDto update(@PathVariable long id,MenuRequestDto request){
        return service.Update(id,request);
    }
}
