package sat.basicbanksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sat.basicbanksystem.entity.Card;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    Optional<Card> findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);

    List<Card> findByUserId(Long id);
}
