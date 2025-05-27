package sat.basicbanksystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bank extends BaseEntity<Long> {

    @Column(nullable = false, unique = true, length = 30)
    String name;

    @OneToMany(mappedBy = "bank")
    List<Card> cards = new ArrayList<>();
}
