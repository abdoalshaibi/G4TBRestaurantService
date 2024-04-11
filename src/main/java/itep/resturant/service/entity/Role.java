package itep.resturant.service.entity;

import lombok.Getter;
import org.springframework.security.access.method.P;

import java.util.List;

@Getter
public enum Role {

    USER(List.of(

            Permission.READ_ALL_CUISINE,
            Permission.SAVE_ONE_CUISINE,
            Permission.DELETE_ONE_CUISINE,
            Permission.UPDATE_ONE_CUISINE,
            Permission.READ_ALL_RESTAURANT,
            Permission.UPDATE_ONE_RESTAURANT,
            Permission.DELETE_ONE_ITEM,
            Permission.READ_ALL_ITEM,
            Permission.SAVE_ONE_ITEM,
            Permission.UPDATE_ONE_ITEM,
            Permission.DELETE_ONE_MENU,
            Permission.READ_ALL_MENU,
            Permission.UPDATE_ONE_MENU,
            Permission.SAVE_ONE_MENU
    )),

    ADMIN(List.of(

            Permission.READ_ALL_CUISINE,
            Permission.SAVE_ONE_CUISINE,
            Permission.DELETE_ONE_CUISINE,
            Permission.UPDATE_ONE_CUISINE,
            Permission.READ_ALL_RESTAURANT,
            Permission.SAVE_ONE_RESTAURANT,
            Permission.DELETE_ONE_RESTAURANT,
            Permission.UPDATE_ONE_RESTAURANT,
            Permission.DELETE_ONE_ITEM,
            Permission.READ_ALL_ITEM,
            Permission.SAVE_ONE_ITEM,
            Permission.UPDATE_ONE_ITEM,
            Permission.DELETE_ONE_MENU,
            Permission.READ_ALL_MENU,
            Permission.UPDATE_ONE_MENU,
            Permission.SAVE_ONE_MENU
    )),

    CUSTOMER (List.of(
            Permission.READ_ALL_RESTAURANT,
            Permission.READ_ALL_MENU,
            Permission.READ_ALL_ITEM
    ));

    private final List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
