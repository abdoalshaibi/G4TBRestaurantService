```mermaid
erDiagram
    Restaurant ||--o{ Menu : places 
    Restaurant {
        long id
        String name
        String location
        String email
        int phone
        String latitude
        String longitude
        boolean isOnline
        long created_by
        long updated_by
        LocalDateTime created_at 
        LocalDateTime updated_at
        int mobile
        String tag
        LocalTime opening_at
        LocalTime closing_at
    }
    Menu ||--|{ Item : contains
    Menu {
     long id
     long restaurantId
     String name
     String image
     String description
     long created_by
     long updated_by
     LocalDateTime closing_at 
     LocalDateTime updated_at
    }
    Item {
     long restaurantId
     long menuId
     String name
     String image
     double price
     LocalDateTime closing_at
     long created_by
     LocalDateTime updated_at
     long updated_by
    }
```
