package sat.basicbanksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sat.basicbanksystem.entity.UserType;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {

    Optional<UserType> findByUserType(String userType);

    boolean existsByUserType(String userType);
}
