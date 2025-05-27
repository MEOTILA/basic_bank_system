package sat.basicbanksystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sat.basicbanksystem.entity.enums.TransactionStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction extends BaseEntity<Long> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "from_card_id", nullable = false)
    Card fromCard;

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_card_id", nullable = false)
    Card toCard;

    @Column(nullable = false)
    Long amount;

    @Column(nullable = false)
    Long fee;

    @Column(nullable = false)
    LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TransactionStatus transactionStatus;
}
