package sat.basicbanksystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserType extends BaseEntity<Long> {

    @Column(nullable = false, unique = true, length = 30)
    private String userType;
}
