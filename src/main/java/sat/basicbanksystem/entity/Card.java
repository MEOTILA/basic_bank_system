package sat.basicbanksystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sat.basicbanksystem.entity.enums.CardStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "bank_id", nullable = false)
    Bank bank;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false)
    Long withdrawLimitation;

    @Column(length = 4, nullable = false)
    String pin;

    @Column(length = 3, nullable = false)
    String cvv2;

    @Column(nullable = false)
    LocalDate expireDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    CardStatus cardStatus = CardStatus.ACTIVE;

    @Column(nullable = false)
    Long failedAttemptCount = 0L;

    @OneToMany(mappedBy = "fromCard", fetch = FetchType.LAZY)
    List<Transaction> sentTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "toCard", fetch = FetchType.LAZY)
    List<Transaction> receivedTransactions = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    LocalDateTime updatedAt;
}
