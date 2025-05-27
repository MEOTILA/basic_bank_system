package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sat.basicbanksystem.service.CardService;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
@Validated
public class CardServiceImpl implements CardService {
}
