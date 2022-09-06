package partymanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import partymanagement.domain.PartyInfo;
import partymanagement.domain.vo.Driver;

public interface PartyInfoService {

    Optional<PartyInfo> findByDriverUserId(String userId);

    Optional<PartyInfo> findByDriver(Driver driver);

    PartyInfo findByMoveInfoStartDate(LocalDateTime times);

    public List<PartyInfo> findAllList(String status, String condition);

    public long registMoveInfo(PartyInfo partyInfo);

}
