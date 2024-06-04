package uzinfocom.uzinfocom.Models;

import jakarta.persistence.*;
import lombok.*;
import uzinfocom.uzinfocom.Models.Enums.OrdersStatuses;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Users client;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrdersStatuses status;

    private Double orderDeliveryExpectedTime;
    private String clientAddress;
    private Double distance;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<Orders_Dishes> orderedDishes;
}