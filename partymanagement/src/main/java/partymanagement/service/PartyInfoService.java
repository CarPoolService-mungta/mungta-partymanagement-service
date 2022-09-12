package partymanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import partymanagement.domain.PartyInfo;
import partymanagement.domain.vo.Driver;

public interface PartyInfoService {

    List<PartyInfo> findByMoveInfoStartDate(String times);

    public List<PartyInfo> findAllList(String status, String condition);

    public long registMoveInfo(PartyInfo partyInfo);

    public List<PartyInfo> findMyList(String status, String condition, String user_id, String user_id2);

    public PartyInfo findById(Long partyId);
}
