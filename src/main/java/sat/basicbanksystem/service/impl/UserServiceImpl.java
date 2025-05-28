package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        isUserExist(user);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        log.info("User with ID {} is saved", savedUser.getId());
    }

    @Override
    public void update(User user) {
        User updatingUser = findById(user.getId());

        if (StringUtils.hasText(user.getFirstName()) &&
                !user.getFirstName().equals(updatingUser.getFirstName())) {
            updatingUser.setFirstName(user.getFirstName());
        }

        if (StringUtils.hasText(user.getLastName()) &&
                !user.getLastName().equals(updatingUser.getLastName())) {
            updatingUser.setLastName(user.getLastName());
        }

        if (StringUtils.hasText(user.getUsername()) &&
                !user.getUsername().equals(updatingUser.getUsername())) {
            checkUsernameExists(user.getUsername());
            updatingUser.setUsername(user.getUsername());
        }

        if (StringUtils.hasText(user.getNationalId()) &&
                !user.getNationalId().equals(updatingUser.getNationalId())) {
            checkNationalIdExists(user.getNationalId());
            updatingUser.setNationalId(user.getNationalId());
        }

        if (StringUtils.hasText(user.getPhoneNumber()) &&
                !user.getPhoneNumber().equals(updatingUser.getPhoneNumber())) {
            checkPhoneNumberExists(user.getPhoneNumber());
            updatingUser.setPhoneNumber(user.getPhoneNumber());
        }

        if (user.getBirthday() != null &&
                !user.getBirthday().equals(updatingUser.getBirthday())) {
            updatingUser.setBirthday(user.getBirthday());
        }

        if (StringUtils.hasText(user.getEmail()) &&
                !user.getEmail().equals(updatingUser.getEmail())) {
            checkEmailExists(user.getEmail());
            updatingUser.setEmail(user.getEmail());
        }

        userRepository.save(updatingUser);
        log.info("User with ID {} is updated", updatingUser.getId());
    }

    @Override
    public void delete(Long id) {
        User deletingUser = findById(id);
        userRepository.delete(deletingUser);
        log.info("User with ID {} is deleted", id);
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

    private void isUserExist(User user){
        checkUsernameExists(user.getUsername());
        checkNationalIdExists(user.getNationalId());
        checkPhoneNumberExists(user.getPhoneNumber());
        checkEmailExists(user.getEmail());
    }

    private void checkUsernameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new CustomApiException("User with username {"
                    + username + "} already exists!",
                    CustomApiExceptionType.UNPROCESSABLE_ENTITY);
        }
    }

    private void checkNationalIdExists(String nationalId) {
        if (userRepository.existsByNationalId(nationalId)) {
            throw new CustomApiException("User with national ID {"
                    + nationalId + "} already exists!",
                    CustomApiExceptionType.UNPROCESSABLE_ENTITY);
        }
    }

    private void checkPhoneNumberExists(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new CustomApiException("User with phone number {"
                    + phoneNumber + "} already exists!",
                    CustomApiExceptionType.UNPROCESSABLE_ENTITY);
        }
    }

    private void checkEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomApiException("User with email {"
                    + email + "} already exists!",
                    CustomApiExceptionType.UNPROCESSABLE_ENTITY);
        }
    }
}
