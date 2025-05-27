package sat.basicbanksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sat.basicbanksystem.entity.Bank;

public interface BankRepository extends JpaRepository<Bank,Long> {
}
