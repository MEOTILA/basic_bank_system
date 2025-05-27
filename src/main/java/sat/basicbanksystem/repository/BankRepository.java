package sat.basicbanksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sat.basicbanksystem.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
}
