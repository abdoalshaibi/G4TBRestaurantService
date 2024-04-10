package itep.resturant.service.service.item;

import itep.resturant.service.entity.Item;
import itep.resturant.service.repository.ItemRepository;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.dao.request.ItemRequest;
import itep.resturant.service.dao.response.ItemResponse;
import itep.resturant.service.service.auth.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository repository;
    private final MenuRepository menuRepository;
    private final AuthenticationService authenticationService;
    private final ModelMapper mapper;

    public ItemServiceImpl(ItemRepository repository, MenuRepository menuRepository, AuthenticationService authenticationService, ModelMapper mapper) {
        this.repository = repository;
        this.menuRepository = menuRepository;
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @Override
    public ItemResponse create(long id , ItemRequest request) {

        var menu= menuRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Item not found with Id: " + id));

        var item = mapper.map(request, Item.class);

         item.setMenu(menu);
         item.setCreatedBy(authenticationService.extractClaims());
         item.setCreatedAt(LocalDateTime.now());
        return mapper.map(repository.save(item), ItemResponse.class);
    }

    @Override
    public List<ItemResponse> getByMenuId(long id) {
        return  repository.findByMenuId(id)
                .stream()
                .map(e -> mapper.map(e, ItemResponse.class))
                .toList();

    }

    @Override
    public ItemResponse update(long id, ItemRequest request) {
        var item= repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with Id: " + id));


        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy(authenticationService.extractClaims());

        mapper.map(request,item);

        return mapper.map(repository.save(item), ItemResponse.class);
    }

    @Override
    public String delete(long id) {
        var item = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with Id: " + id));

        repository.delete(item);

        return "Item " + id + " is deleted";
    }

}
