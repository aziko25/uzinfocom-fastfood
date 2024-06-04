package uzinfocom.uzinfocom.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "dishes")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Dishes_Categories category;
}