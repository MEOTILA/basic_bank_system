package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sat.basicbanksystem.entity.UserType;
import sat.basicbanksystem.exception.CustomApiException;
import sat.basicbanksystem.exception.CustomApiExceptionType;
import sat.basicbanksystem.repository.UserTypeRepository;
import sat.basicbanksystem.service.UserTypeService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
@Validated
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;

    @Override
    public void save(UserType userType) {
        userTypeRepository.save(userType);
    }

    @Override
    public void update(Long id) {
        UserType updatingUserType = findById(id);
        userTypeRepository.save(updatingUserType);
    }

    @Override
    public void delete(Long id) {
        UserType deletingUserType = findById(id);
        userTypeRepository.delete(deletingUserType);
    }

    @Override
    public UserType findById(Long id) {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("UserType with ID {"
                        + id + "} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public UserType findByUserType(String userType) {
        return userTypeRepository.findByUserType(userType)
                .orElseThrow(() -> new CustomApiException("UserType {"
                        + userType + "} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }
}
