package itep.resturant.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import itep.resturant.service.service.ItemsRequestDto;
import itep.resturant.service.service.ItemsResponseDto;
import itep.resturant.service.service.ItemsService;


@RestController
@RequestMapping("/api/v1/Items")
public class ItemsController {

     @Autowired
     private  ItemsService service;



     @PostMapping
    public ItemsResponseDto Creat(@RequestBody ItemsRequestDto request) {
        return service.Create(request);
    }

   @GetMapping
    public List<ItemsResponseDto> getAll() {
        return service.GetAll();
    }


     @PutMapping("/{id}")
    public ItemsResponseDto update(@PathVariable long id, @RequestBody ItemsRequestDto request) {
        return service.Update(id,request);
    }

    
}
