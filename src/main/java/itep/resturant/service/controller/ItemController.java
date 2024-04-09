package itep.resturant.service.controller;

import itep.resturant.service.dao.request.ItemRequestDto;
import itep.resturant.service.service.item.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> create(@PathVariable long id,@Valid @RequestBody ItemRequestDto request) {

        try
        {
            return ResponseEntity.ok( service.create(id, request));

        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id,@Valid @RequestBody ItemRequestDto request)
    {
        try
        {
            return ResponseEntity.ok( service.update(id,request));

        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable long id)
    {
        try
        {
            return ResponseEntity.ok(service.getByMenuId(id));

        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id)
    {
        try
        {
            return ResponseEntity.ok(service.delete(id));

        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

}
