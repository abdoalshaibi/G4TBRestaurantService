package itep.resturant.service.entity.local;

import lombok.Getter;

import java.util.List;

@Getter
public enum Role {

    USER(List.of(

            Permission.SAVE_ONE_CUISINE,
            Permission.DELETE_ONE_CUISINE,
            Permission.UPDATE_ONE_CUISINE,
            Permission.UPDATE_ONE_RESTAURANT,
            Permission.DELETE_ONE_ITEM,
            Permission.SAVE_ONE_ITEM,
            Permission.UPDATE_ONE_ITEM,
            Permission.DELETE_ONE_MENU,
            Permission.UPDATE_ONE_MENU,
            Permission.SAVE_ONE_MENU,
            Permission.GET_ALL_ORDERS,
            Permission.Change_ORDER_Status
    )),

    ADMIN(List.of(

            Permission.SAVE_ONE_CUISINE,
            Permission.DELETE_ONE_CUISINE,
            Permission.UPDATE_ONE_CUISINE,
            Permission.SAVE_ONE_RESTAURANT,
            Permission.DELETE_ONE_RESTAURANT,
            Permission.UPDATE_ONE_RESTAURANT,
            Permission.DELETE_ONE_ITEM,
            Permission.SAVE_ONE_ITEM,
            Permission.UPDATE_ONE_ITEM,
            Permission.DELETE_ONE_MENU,
            Permission.UPDATE_ONE_MENU,
            Permission.SAVE_ONE_MENU,
            Permission.GET_ALL_ORDERS,
            Permission.Change_ORDER_Status
    ));

    private final List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
