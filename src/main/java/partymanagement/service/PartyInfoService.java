package partymanagement.service;

import java.util.List;

import partymanagement.domain.PartyInfo;
import partymanagement.domain.event.MatchAccepted;
import partymanagement.domain.event.MatchCancelled;
import partymanagement.domain.event.PartyRegistered;
import partymanagement.domain.event.PartyStatusChanged;
import partymanagement.domain.request.PayCheckRequest;
import partymanagement.domain.response.PartyAccusationResponse;
import partymanagement.domain.response.PartyInfoResponse;
import partymanagement.domain.vo.CarPooler;
import partymanagement.exception.MessageEntity;

public interface PartyInfoService {

    List<PartyInfo> findByMoveInfoStartDate(String times);

    // public List<PartyInfo> findAllList(String status, String condition);

    public long registMoveInfo(PartyInfo partyInfo);
    public MessageEntity deleteMoveInfo(Long partyId);
    //public List<PartyInfo> findAllList(String status, String search_condition,String order_condition, String value);
    public List<PartyInfo> findAllList(String status, String departure, String destination, String start_date, String order, String userId);
 //   public List<PartyInfo> findMyList(String status, String condition, String user_id, String user_id2);
    // public List<PartyInfo> findMyList(String status, String search_condition, String order_condition, String value, String user_id, String user_id2);
    public List<PartyInfo> findMyList(String status, String departure, String destination, String start_date, String order, String user_id);
    public PartyInfo findById(Long partyId);
    public PartyInfoResponse getPost(Long id);
    public List<PartyInfoResponse> getPartyList(List<Long> partyIds);
    public List<PartyInfoResponse> getAllList(String status, String departure, String destination, String start_date, String condition, String userId);
    public List<PartyInfoResponse> getMyList(String status, String departure, String destination, String start_date, String condition, String user_id);
    public List<String> findUserIdList(Long partyId);
    public PartyAccusationResponse getSummaryInfo(Long partyId);
    public MessageEntity addCarpooler(Long partyId, CarPooler carpooler);
    public MessageEntity removeCarpooler(Long partyId, CarPooler carpooler);
    public void changePartyStatus(PartyStatusChanged partyStatusChanged);
    public void acceptCarpooler(MatchAccepted matchAccepted);
    public void cancelCarpoolerApply(MatchCancelled matchCancelled);
    public long registMoveInfoRollback(PartyRegistered partyRegistered);
    public void retryPayment(PayCheckRequest payCheckRequest);
    public void checkPayment(PayCheckRequest payCheckRequest);
    public void requestPayCheck(PayCheckRequest payCheckRequest);
}
