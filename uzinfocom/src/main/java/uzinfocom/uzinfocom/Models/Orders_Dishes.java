package uzinfocom.uzinfocom.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@IdClass(Orders_Dishes_Ids.class)
@Table(name = "orders_dishes")
public class Orders_Dishes {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orderId;

    @Id
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dishes dishId;

    private Integer quantity;
}