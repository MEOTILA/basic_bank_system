package sat.basicbanksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sat.basicbanksystem.entity.Card;

public interface CardRepository extends JpaRepository<Card,Long> {
}
