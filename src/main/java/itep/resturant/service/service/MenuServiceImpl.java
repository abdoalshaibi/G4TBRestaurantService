package itep.resturant.service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itep.resturant.service.entity.Menu;
import itep.resturant.service.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService{

   @Autowired
    MenuRepository repository;

    @Autowired 
    ModelMapper mapper;

    @Override
    public MenuResponseDto Create(MenuRequestDto request) {

        var Menu = mapper.map(request, Menu.class);

        return mapper.map(repository.save(Menu),MenuResponseDto.class);
    }

    @Override
    public List<MenuResponseDto> GetAll() {

        return repository.findAll()
                .stream()
                .map(e->mapper.map(e,MenuResponseDto.class))
                .toList();


    }

    @Override
    public MenuResponseDto Update(long id, MenuRequestDto request) {


        var Menu = repository.findById(id).get();


        mapper.map(request,Menu);

         return mapper.map(repository.save(Menu),MenuResponseDto.class);
    }

    
}
