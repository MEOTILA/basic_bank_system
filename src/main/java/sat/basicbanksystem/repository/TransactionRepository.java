package sat.basicbanksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sat.basicbanksystem.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
