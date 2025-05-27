package sat.basicbanksystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity<Long> {

    @Column(length = 25, nullable = false)
    String firstName;

    @Column(length = 50, nullable = false)
    String lastName;

    @Column(length = 25, nullable = false, unique = true)
    String username;

    @Column(length = 250, nullable = false)
    String password;

    @Column(length = 10, nullable = false, unique = true)
    String nationalId;

    @Column(length = 11, nullable = false, unique = true)
    String phoneNumber;

    @Column(nullable = false)
    LocalDate birthday;

    @Column(length = 50, nullable = false, unique = true)
    String email;

    @ManyToOne(optional = false)
    UserType userType;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    LocalDateTime updatedAt;


}
