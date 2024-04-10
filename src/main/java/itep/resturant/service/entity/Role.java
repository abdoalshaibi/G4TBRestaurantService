package itep.resturant.service.entity;

import lombok.Getter;
import org.springframework.security.access.method.P;

import java.util.List;

@Getter
public enum Role {

    USER(List.of(

            Permission.READ_ALL_RESTAURANT,
            Permission.SAVE_ONE_CUISINE
    )),

    ADMIN(List.of(

            Permission.READ_ALL_CUISINE,
            Permission.SAVE_ONE_CUISINE,
            Permission.DELETE_ONE_CUISINE,
            Permission.UPDATE_ONE_CUISINE,
            Permission.READ_ALL_RESTAURANT,
            Permission.SAVE_ONE_RESTAURANT,
            Permission.DELETE_ONE_RESTAURANT,
            Permission.UPDATE_ONE_RESTAURANT
    ));

    private final List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
