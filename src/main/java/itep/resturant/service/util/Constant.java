package itep.resturant.service.util;

import java.util.Map;

import static java.util.Map.entry;

public class Constant {

    private Constant() {
        throw new IllegalStateException("Utility class");
    }

    public static final boolean RESULT_SUCCESS = true;
    public static final boolean RESULT_FAIL = false;

    public static Map<String, String> getLogResponseHashMap() {
        return Map.<String, String>ofEntries(
                entry("RESTAURANT-" + "1", "Restaurant already exists"),
                entry("RESTAURANT-" + "2", "Restaurant not created"),
                entry("RESTAURANT-" + "3", "Restaurant not updated"),
                entry("RESTAURANT-" + "4", "Restaurant not deleted"),
                entry("RESTAURANT-" + "5", "Restaurant not found"),
                entry("RESTAURANT-" + "6", "Restaurant not found"),
                entry("RESTAURANT-" + "7", "Restaurant created"),
                entry("RESTAURANT-" + "8", "Restaurant updated"),
                entry("RESTAURANT-" + "9", "Restaurant deleted"),
                entry("RESTAURANT-" + "10", "Email already taken"),
                entry("RESTAURANT-" + "11", "Phone already taken"),
                entry("RESTAURANT-" + "12", "Mobile already taken"),
                entry("RESTAURANT-" + "13", "Status changed successfully"),

                entry("USER-" + "1", "User already exists"),
                entry("USER-" + "2", "User not created"),
                entry("USER-" + "3", "User not updated"),
                entry("USER-" + "4", "User not deleted"),
                entry("USER-" + "5", "User not found"),
                entry("USER-" + "6", "User not found"),
                entry("USER-" + "7", "User created"),
                entry("USER-" + "8", "User updated"),
                entry("USER-" + "9", "User deleted"),
                entry("USER-" + "10", "Unknown Error"),

                entry("ITEM-" + "1", "Item already exists"),
                entry("ITEM-" + "2", "Item not created"),
                entry("ITEM-" + "3", "Item not updated"),
                entry("ITEM-" + "4", "Item not deleted"),
                entry("ITEM-" + "5", "Item not found"),
                entry("ITEM-" + "6", "Item not found"),
                entry("ITEM-" + "7", "Item created"),
                entry("ITEM-" + "8", "Item updated"),
                entry("ITEM-" + "9", "Item deleted"),

                entry("MENU-" + "1", "Menu already exists"),
                entry("MENU-" + "2", "Menu not created"),
                entry("MENU-" + "3", "Menu not updated"),
                entry("MENU-" + "4", "Menu not deleted"),
                entry("MENU-" + "5", "Menu not found"),
                entry("MENU-" + "6", "Menu not found"),
                entry("MENU-" + "7", "Menu created"),
                entry("MENU-" + "8", "Menu updated"),
                entry("MENU-" + "9", "Menu deleted"),
                entry("MENU-" + "10", "Menu can not delete. it has items"),
                entry("MENU-" + "11", "Unknown Error"),

                entry("CUISINE-" + "1", "Cuisine already exists"),
                entry("CUISINE-" + "2", "Cuisine not created"),
                entry("CUISINE-" + "3", "Cuisine not updated"),
                entry("CUISINE-" + "4", "Cuisine not deleted"),
                entry("CUISINE-" + "5", "Cuisine not found"),
                entry("CUISINE-" + "6", "Cuisine not found"),
                entry("CUISINE-" + "7", "Cuisine created"),
                entry("CUISINE-" + "8", "Cuisine updated"),
                entry("CUISINE-" + "9", "Cuisine deleted")
        );
    }
}
