package itep.resturant.service.controller;

import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.dto.MenuResponseDto;
import itep.resturant.service.service.menu.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {


    MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping(value = "/{id}",name = "id is restaurant identity")
    public MenuResponseDto Create(@PathVariable long id, @RequestBody MenuRequestDto request)
    {
         return service.Create(id,request);
    }
    @GetMapping(value = "/{id}",name = "id is restaurant identity")
    public List<MenuResponseDto> getAll(@PathVariable long id)
    {
        return service.GetById(id);
    }

    @PutMapping("/{id}")
    public MenuResponseDto update(@PathVariable long id,@RequestBody MenuRequestDto request){
        return service.Update(id,request);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable long id)
    {
        service.Delete(id);
    }
}
