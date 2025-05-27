package sat.basicbanksystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity<Long> {

    @Column(nullable = false)
    Long balance;

    @Column(nullable = false, unique = true, length = 16)
    String cardNumber;

    @ManyToOne(optional = false)
    Bank bank;

    @ManyToOne(optional = false)
    User user;

    @Column(nullable = false)
    Long withdrawLimitation;
}
