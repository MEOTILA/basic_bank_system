package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.UserType;

import java.util.List;

public interface UserTypeService {

    void save(UserType userType);

    void update(UserType userType);

    void delete(Long id);

    UserType findById(Long id);

    UserType findByUserType(String userType);

    List<UserType> findAll();
}
