package partymanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import partymanagement.domain.PartyInfo;
import partymanagement.domain.response.PartyAccusationResponse;
import partymanagement.domain.response.PartyInfoResponse;
import partymanagement.domain.vo.CarPooler;
import partymanagement.domain.vo.Driver;
import partymanagement.exception.MessageEntity;

public interface PartyInfoService {

    List<PartyInfo> findByMoveInfoStartDate(String times);

    // public List<PartyInfo> findAllList(String status, String condition);

    public long registMoveInfo(PartyInfo partyInfo);
    //public List<PartyInfo> findAllList(String status, String search_condition,String order_condition, String value);
    public List<PartyInfo> findAllList(String status, String departure, String destination, String start_date, String order);
 //   public List<PartyInfo> findMyList(String status, String condition, String user_id, String user_id2);
    // public List<PartyInfo> findMyList(String status, String search_condition, String order_condition, String value, String user_id, String user_id2);
    public List<PartyInfo> findMyList(String status, String departure, String destination, String start_date, String order, String user_id, String user_id2);
    public PartyInfo findById(Long partyId);
    public PartyInfoResponse getPost(Long id);
    public List<PartyInfoResponse> getAllList(String status, String departure, String destination, String start_date, String condition);
    public List<PartyInfoResponse> getMyList(String status, String departure, String destination, String start_date, String condition, String user_id);
    public List<String> findUserIdList(Long partyId);
    public PartyAccusationResponse getSummaryInfo(Long partyId);
    public MessageEntity addCarpooler(Long partyId, CarPooler carpooler);
    public MessageEntity removeCarpooler(Long partyId, CarPooler carpooler);
}
