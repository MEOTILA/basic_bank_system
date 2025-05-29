package sat.basicbanksystem.service;

import sat.basicbanksystem.dto.UserCardResponseDTO;

import java.util.List;

public interface UserCardService {
    List<UserCardResponseDTO> getUserCardsInfo(Long userId);
}
