package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sat.basicbanksystem.entity.User;
import sat.basicbanksystem.exception.CustomApiException;
import sat.basicbanksystem.exception.CustomApiExceptionType;
import sat.basicbanksystem.repository.UserRepository;
import sat.basicbanksystem.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
@Validated
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(Long id) {
        User updatingUser = findById(id);
        userRepository.save(updatingUser);
    }

    @Override
    public void delete(Long id) {
        User deletingUser = findById(id);
        userRepository.delete(deletingUser);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("User with ID {"
                        + id + "} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomApiException("User with username {"
                        + username + "} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
