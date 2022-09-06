package partymanagement.service;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import partymanagement.domain.PartyInfo;

public interface PartyInfoService {

    Optional<PartyInfo> findByDriverUserId(String userId);

    Optional<PartyInfo> findByDriver(Driver driver);

    PartyInfo findByMoveInfoStartDate(LocalDateTime times);

    public List<PartyInfo> findAllList(String status, String condition);
}
