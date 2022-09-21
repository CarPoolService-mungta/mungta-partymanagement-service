package partymanagement.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import partymanagement.domain.PartyInfo;
import partymanagement.domain.enumeration.CarpoolingStatus;
import partymanagement.domain.enumeration.PayCheck;
import partymanagement.domain.event.MatchAccepted;
import partymanagement.domain.event.MatchCancelled;
import partymanagement.domain.event.PartyRegistered;
import partymanagement.domain.event.PartyStatusChanged;
import partymanagement.domain.repository.PartyInfoRepository;
import partymanagement.domain.response.PartyAccusationResponse;
import partymanagement.domain.response.PartyInfoResponse;
import partymanagement.domain.response.UserResponse;
import partymanagement.domain.vo.CarPooler;
import partymanagement.exception.ApiException;
import partymanagement.exception.ApiStatus;
import partymanagement.exception.MessageEntity;
import partymanagement.infra.KafkaProducer;
import partymanagement.infra.UserServiceClient;

@Service
@RequiredArgsConstructor
public class PartyInfoServiceImpl implements PartyInfoService{

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final PartyInfoRepository partyInfoRepository;

    private final KafkaProducer kafkaProducer;

    private final UserServiceClient userServiceClient;

    @Override
    public List<PartyInfo> findByMoveInfoStartDate(String times) {
        List<PartyInfo> res = partyInfoRepository.findByMoveInfoStartDate(times);
        return res;
    }

    @Override
    public List<PartyInfo> findAllList(String status, String departure, String destination, String start_date, String order, String userId){
        if(departure == null) departure ="";
        if(destination == null) destination ="";
        if(start_date == null) start_date ="";
        if(order == null) order ="";
        if(status.equals("now"))
        {
            switch(order){
                case "start_date": return partyInfoRepository.findAllNowListByStartDateDesc(userId);
                case "review_average_score": return partyInfoRepository.findAllNowListByReviewAverageScoreDesc(userId);
                default : return partyInfoRepository.findAllNowListNoCondition(userId);
            }
        }else{
            switch(order){
                case "start_date": return partyInfoRepository.findAllPastListByStartDateDesc(userId);
                case "review_average_score": return partyInfoRepository.findAllPastListByReviewAverageScoreDesc(userId);
                default : return partyInfoRepository.findAllPastListNoCondition(userId);
            }
        }
    }
    @Override
    public List<PartyInfo> findMyList(String status, String departure, String destination, String start_date, String order, String user_id){
        if(departure == null) departure ="";
        if(destination == null) destination ="";
        if(start_date == null) start_date ="";
        if(order == null) order ="";
        if(status.equals("now"))
        {
            switch(order){
                case "start_date": return partyInfoRepository.findMyNowListByStartDateDesc(user_id);
                case "review_average_score": return partyInfoRepository.findMyNowListByReviewAverageScoreDesc(user_id);
                default : return partyInfoRepository.findMyNowListNoCondition(user_id);
            }
        }else{
            switch(order){
                case "start_date": return partyInfoRepository.findMyPastListByStartDateDesc(user_id);
                case "review_average_score": return partyInfoRepository.findMyPastListByReviewAverageScoreDesc(user_id);
                default : return partyInfoRepository.findMyPastListNoCondition(user_id);
            }
        }
    }

    @Override
    public List<PartyInfoResponse> getAllList(String status, String departure, String destination, String start_date, String condition, String userId){
        List<PartyInfo> res = findAllList(status, departure, destination, start_date, condition, userId);
        List<PartyInfoResponse> getAllList = res.stream().map(PartyInfoResponse::of).collect(Collectors.toList());
        return getAllList;
    }
    @Override
    public List<PartyInfoResponse> getMyList(String status, String departure, String destination, String start_date, String condition, String user_id){
        List<PartyInfo> res = findMyList(status, departure, destination, start_date, condition, user_id);
        List<PartyInfoResponse> getMyList = res.stream().map(PartyInfoResponse::of).collect(Collectors.toList());
        return getMyList;
    }

    @Override
    @Transactional
    public long registMoveInfo(PartyInfo partyInfo) {
        partyInfo.addCarpooler(CarPooler.builder()
                        .userId(partyInfo.getDriver().getUserId())
                        .name(partyInfo.getDriver().getName())
                        .department(partyInfo.getDriver().getDepartment())
                        .carpoolerCheck(PayCheck.PAID)
                        .driverCheck(PayCheck.PAID)
                        .carpoolingStatus(CarpoolingStatus.FORMED)
                        .startDate(partyInfo.getMoveInfo().getStartDate())
                .build()
        );
        long id = partyInfoRepository.save(partyInfo).getId();


        kafkaProducer.send("partyRegistered-out", new PartyRegistered(partyInfo));

        System.out.println("#운전정보 등록 id: "+ id);
        return id;
    }
    @Override
    public MessageEntity deleteMoveInfo(Long partyId) {
        partyInfoRepository.delete(partyInfoRepository.findById(partyId).orElseThrow(() -> new ApiException(ApiStatus.CANNOT_REMOVE_PARTYINFO)));
        return MessageEntity.of(ApiStatus.REMOVED_PARTYINFO);
    }

    @Override
    public List<String> findUserIdList(Long partyId){
        List<String> res = partyInfoRepository.findUserIdList(partyId, partyId);
        return res;
    }

    @Override
    public PartyAccusationResponse getSummaryInfo(Long partyId){
        PartyInfo partyInfo = findById(partyId);
        List<String> userIdList = findUserIdList(partyId);
        return PartyAccusationResponse.builder()
                                    .partyId(partyId)
                                    .placeOfDeparture(partyInfo.getMoveInfo().getPlaceOfDeparture())
                                    .destination(partyInfo.getMoveInfo().getDestination())
                                    .startDate(LocalDateTimetoString(partyInfo.getMoveInfo().getStartDate()))
                                    .userIds(userIdList).build();
    }
    @Override
    public PartyInfoResponse getPost(Long id){
        PartyInfo partyInfo = findById(id);
        return PartyInfoResponse.of(partyInfo);
    }
    @Override
    public PartyInfo findById(Long partyId){
        //return partyInfoRepository.findById(partyId).orElseGet(PartyInfo::new);
        return partyInfoRepository.findById(partyId).orElseThrow(() -> new ApiException(ApiStatus.NOT_FOUND_PARTYINFO));
    }

    public static String LocalDateTimetoString(LocalDateTime localDateTime) {
           return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.KOREA).format(localDateTime);
    }

    @Override
    @Deprecated
    public MessageEntity addCarpooler(Long partyId, CarPooler carpooler){
        PartyInfo partyInfo = findById(partyId);
        List<CarPooler> carPoolers = partyInfo.getCarPoolers();
        System.out.println("added carpoolers");
        try{
            if(carPoolers.contains(carpooler)){
                System.out.println("이미 있어");
                return MessageEntity.of(ApiStatus.CANNOT_ADD_CARPOOLER);
            }
            System.out.print("ADD 전");
            for(CarPooler c : carPoolers){
                System.out.println(c);
            }
            carPoolers.add(carpooler);
            System.out.print("ADD 후");
            for(CarPooler c : carPoolers){
                System.out.println(c);
            }
            partyInfo.setCarPoolers(carPoolers);
            partyInfoRepository.save(partyInfo);
        }
        catch(Exception e){
            e.getStackTrace();
        }
        return MessageEntity.of(ApiStatus.ADDED_CARPOOLER);

    }

    @Override
    @Deprecated
    public MessageEntity removeCarpooler(Long partyId, CarPooler carpooler){
        PartyInfo partyInfo = findById(partyId);
        List<CarPooler> carPoolers = partyInfo.getCarPoolers();
        System.out.println("removed carpoolers");
        try{
            if(!carPoolers.contains(carpooler)){
                return MessageEntity.of(ApiStatus.CANNOT_REMOVE_CARPOOLER);
            }
            System.out.print("REMOVE 전");
            for(CarPooler c : carPoolers){
                System.out.println(c);
            }
            carPoolers.remove(carPoolers.indexOf(carpooler));
            System.out.print("REMOVE 후");
            for(CarPooler c : carPoolers){
                System.out.println(c);
            }
            partyInfo.setCarPoolers(carPoolers);
            partyInfoRepository.save(partyInfo);
        }
        catch(Exception e){

            e.getStackTrace();
        }
        return MessageEntity.of(ApiStatus.REMOVED_CARPOOLER);

    }

    @Override
    @Transactional
    public void changePartyStatus(PartyStatusChanged partyStatusChanged){
        PartyInfo partyInfo = findById(partyStatusChanged.getPartyInfoId());
        partyInfo.setStatus(partyStatusChanged.getPartyStatus());
    }

    @Override
    @Transactional
    public void acceptCarpooler(MatchAccepted matchAccepted){
        PartyInfo partyInfo = findById(matchAccepted.getPartyInfoId());
        List<UserResponse> userResponseList = userServiceClient.getUserList(new ArrayList<>(){
            {add(matchAccepted.getUserId());}
        });

        CarPooler carPooler=CarPooler.builder()
                .userId(matchAccepted.getUserId())
                .name(userResponseList.get(0).getUserName())
                .department(userResponseList.get(0).getUserTeamName())
                .carpoolingStatus(CarpoolingStatus.ACCEPT)
                .carpoolerCheck(PayCheck.NOTPAID)
                .driverCheck(PayCheck.NOTPAID)
                .build();

        partyInfo.addCarpooler(carPooler);
    }

    @Override
    @Transactional
    public void cancelCarpoolerApply(MatchCancelled matchCancelled){
        PartyInfo partyInfo = findById(matchCancelled.getPartyInfoId());
        CarPooler carPooler= partyInfo.getCarPoolers().stream()
                                    .filter(o->o.getUserId().equals(matchCancelled.getUserId()))
                                    .findFirst()
                                    .orElse(null);

        if(Objects.isNull(carPooler)){
            new ApiException(ApiStatus.CANNOT_REMOVE_CARPOOLER);
        }
        carPooler.setCarpoolingStatus(CarpoolingStatus.CANCEL);

    }

}
