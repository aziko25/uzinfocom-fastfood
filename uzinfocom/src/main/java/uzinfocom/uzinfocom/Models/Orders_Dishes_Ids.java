package uzinfocom.uzinfocom.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders_Dishes_Ids implements Serializable {

    private Long orderId;
    private Long dishId;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Orders_Dishes_Ids that = (Orders_Dishes_Ids) o;

        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, dishId);
    }
}