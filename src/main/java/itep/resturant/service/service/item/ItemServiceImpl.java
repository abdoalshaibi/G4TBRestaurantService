package itep.resturant.service.service.item;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.entity.Item;
import itep.resturant.service.repository.ItemRepository;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.dao.request.ItemRequest;
import itep.resturant.service.dao.response.ItemResponse;
import itep.resturant.service.service.auth.AuthenticationService;
import itep.resturant.service.util.Constant;
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
    public APIResponse<ItemResponse> create(long id , ItemRequest request) {

        var menuOptional= menuRepository.findById(id);

        if (menuOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(), "ITEM-".concat("1"));

        var item = mapper.map(request, Item.class);

         item.setMenu(menuOptional.get());
         item.setCreatedBy(authenticationService.extractClaims());
         item.setCreatedAt(LocalDateTime.now());

        return APIResponse.ok(mapper.map(repository.save(item), ItemResponse.class), Constant.getLogResponseHashMap(), "ITEM-".concat("2"));

    }

    @Override
    public APIResponse<List<ItemResponse>> getByMenuId(long id) {
        var item=  repository.findByMenuId(id)
                .stream()
                .map(e -> mapper.map(e, ItemResponse.class))
                .toList();

        return APIResponse.ok(item, Constant.getLogResponseHashMap(), "ITEM-".concat("3"));


    }

    @Override
    public APIResponse<ItemResponse> update(long id, ItemRequest request) {
        var itemOptional= repository.findById(id);

        if (itemOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(), "ITEM-".concat("1"));


        var item = itemOptional.get();

        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy(authenticationService.extractClaims());

        mapper.map(request,item);


        return APIResponse.ok(mapper.map(repository.save(item), ItemResponse.class), Constant.getLogResponseHashMap(), "ITEM-".concat("3"));

    }

    @Override
    public APIResponse<String> delete(long id) {
        var itemOptional = repository.findById(id);

        if (itemOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(), "ITEM-".concat("1"));

        repository.delete(itemOptional.get());

        return APIResponse.ok(null, Constant.getLogResponseHashMap(), "ITEM-".concat("9"));

    }

}
