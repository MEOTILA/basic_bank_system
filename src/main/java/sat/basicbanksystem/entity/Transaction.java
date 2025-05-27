package sat.basicbanksystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import sat.basicbanksystem.entity.base.BaseEntity;
import sat.basicbanksystem.entity.enums.TransactionStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;
}
