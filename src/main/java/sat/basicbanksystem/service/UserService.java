package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void update(User user);

    void delete(Long id);

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();
}
