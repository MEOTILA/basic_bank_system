package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;

    @Override
    public void save(UserType userType) {
        checkUserTypeExists(userType.getUserType());
        UserType savedUserType = userTypeRepository.save(userType);
        log.info("UserType with ID {} is saved", savedUserType.getId());
    }

    @Override
    public void update(UserType userType) {
        UserType updatingUserType = findById(userType.getId());

        if (StringUtils.hasText(userType.getUserType()) &&
                !userType.getUserType().equals(updatingUserType.getUserType())) {
            checkUserTypeExists(userType.getUserType());
            updatingUserType.setUserType(userType.getUserType());
        }

        userTypeRepository.save(updatingUserType);
        log.info("UserType with ID {} is updated", updatingUserType.getId());
    }

    @Override
    public void delete(Long id) {
        UserType deletingUserType = findById(id);
        userTypeRepository.delete(deletingUserType);
        log.info("UserType with ID {} is deleted", id);
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

    private void checkUserTypeExists(String userType) {
        if (userTypeRepository.existsByUserType(userType)) {
            throw new CustomApiException("UserType {"
                    + userType + "} already exists!",
                    CustomApiExceptionType.UNPROCESSABLE_ENTITY);
        }
    }
}
