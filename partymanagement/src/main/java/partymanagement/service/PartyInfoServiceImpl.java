package partymanagement.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import partymanagement.domain.PartyInfo;
import partymanagement.domain.repository.PartyInfoRepository;
import partymanagement.domain.response.PartyAccusationResponse;
import partymanagement.domain.response.PartyInfoResponse;
import partymanagement.domain.vo.CarPooler;
import partymanagement.domain.vo.CarPooler;
import partymanagement.domain.vo.Driver;
import partymanagement.domain.vo.MoveInfo;
import partymanagement.exception.ApiException;
import partymanagement.exception.ApiStatus;
import partymanagement.exception.MessageEntity;

@Service
public class PartyInfoServiceImpl implements PartyInfoService{

    @Autowired
    PartyInfoRepository partyInfoRepository;

    @Override
    public List<PartyInfo> findByMoveInfoStartDate(String times) {
        List<PartyInfo> res = partyInfoRepository.findByMoveInfoStartDate(times);
        return res;
    }

    // @Override
    // public List<PartyInfo> findAllList(String status, String order_condition) {
    //     if(order_condition == null) order_condition ="";
    //     System.out.println("현재 상태는 "+status+" 현재 정렬 조건은 "+order_condition);
    //     if(status.equals("now")){
    //         switch(order_condition){
    //             case "start_date": return partyInfoRepository.findAllNowListByStartDateDesc();
    //             case "review_average_score": return partyInfoRepository.findAllNowListByReviewAverageScoreDesc();
    //             default : return partyInfoRepository.findAllNowListNoCondition();
    //         }
    //         //return order_condition.equals("")?partyInfoRepository.findAllNowListNoCondition() : partyInfoRepository.findAllNowListSort(Sort.by(Sort.Direction.DESC,order_condition));
    //     }else{
    //         switch(order_condition){
    //             case "start_date": return partyInfoRepository.findAllPastListByStartDateDesc();
    //             case "review_average_score": return partyInfoRepository.findAllPastListByReviewAverageScoreDesc();
    //             default : return partyInfoRepository.findAllPastListNoCondition();
    //         }
    //         //return order_condition.equals("")?partyInfoRepository.findAllPastListNoCondition() : partyInfoRepository.findAllPastList(order_condition);
    //     }
    // }
    @Override
    public List<PartyInfo> findAllList(String status, String departure, String destination, String start_date, String order){
        if(departure == null) departure ="";
        if(destination == null) destination ="";
        if(start_date == null) start_date ="";
        if(order == null) order ="";
        if(status.equals("now"))
        {
            switch(order){
                case "start_date": return partyInfoRepository.findAllNowListByStartDateDesc();
                case "review_average_score": return partyInfoRepository.findAllNowListByReviewAverageScoreDesc();
                default : return partyInfoRepository.findAllNowListNoCondition();
            }
        }else{
            switch(order){
                case "start_date": return partyInfoRepository.findAllPastListByStartDateDesc();
                case "review_average_score": return partyInfoRepository.findAllPastListByReviewAverageScoreDesc();
                default : return partyInfoRepository.findAllPastListNoCondition();
            }
        }
    }
/*
    // @Override
    // public List<PartyInfo> findAllList(String status, String search_condition, String order_condition, String value) {
    //     if(search_condition == null) search_condition ="";
    //     if(order_condition == null) order_condition ="";
    //     System.out.println("[status:"+status+" order_condition:"+order_condition+ "value :"+value+")]");
    //     if(status.equals("now"))
    //     {
    //        if(search_condition.equals("start_date")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findAllNowListByStartDateOrderByStartDateDesc(value);
    //                 case "review_average_score": return partyInfoRepository.findAllNowListByStartDateOrderByReviewAverageScoreDesc(value);
    //                 default : return partyInfoRepository.findAllNowListNoCondition();
    //             }
    //        }else if(search_condition.equals("department")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findAllNowListByDepartmentOrderByStartDateDesc(value);
    //                 case "review_average_score": return partyInfoRepository.findAllNowListByDepartmentOrderByReviewAverageScoreDesc(value);
    //                 default : return partyInfoRepository.findAllNowListByDepartmentNoOrder(value);
    //             }
    //        }else if(search_condition.equals("destination")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findAllNowListByDestinationOrderByStartDateDesc(value);
    //                 case "review_average_score": return partyInfoRepository.findAllNowListByDestinationOrderByReviewAverageScoreDesc(value);
    //                 default : return partyInfoRepository.findAllNowListByDestinationNoOrder(value);
    //             }
    //        }else{
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findAllNowListByStartDateDesc();
    //                 case "review_average_score": return partyInfoRepository.findAllNowListByReviewAverageScoreDesc();
    //                 default : return partyInfoRepository.findAllNowListNoCondition();
    //             }
    //         }
    //     }
    //     else{
    //         switch(order_condition){
    //             case "start_date": return partyInfoRepository.findAllPastListByStartDateDesc();
    //             case "review_average_score": return partyInfoRepository.findAllPastListByReviewAverageScoreDesc();
    //             default : return partyInfoRepository.findAllPastListNoCondition();
    //         }
    //     }
    // }
*/
    @Override
    public List<PartyInfo> findMyList(String status, String departure, String destination, String start_date, String order, String user_id, String user_id2){
        if(departure == null) departure ="";
        if(destination == null) destination ="";
        if(start_date == null) start_date ="";
        if(order == null) order ="";
        if(status.equals("now"))
        {
            switch(order){
                case "start_date": return partyInfoRepository.findMyNowListByStartDateDesc(user_id,user_id2);
                case "review_average_score": return partyInfoRepository.findMyNowListByReviewAverageScoreDesc(user_id,user_id2);
                default : return partyInfoRepository.findMyNowListNoCondition(user_id,user_id2);
            }
        }else{
            switch(order){
                case "start_date": return partyInfoRepository.findMyPastListByStartDateDesc(user_id,user_id2);
                case "review_average_score": return partyInfoRepository.findMyPastListByReviewAverageScoreDesc(user_id,user_id2);
                default : return partyInfoRepository.findMyPastListNoCondition(user_id,user_id2);
            }
        }
    }

    public List<PartyInfoResponse> getAllList(String status, String departure, String destination, String start_date, String condition){
        List<PartyInfo> res = findAllList(status, departure, destination, start_date, condition);
        List<PartyInfoResponse> getAllList = res.stream().map(PartyInfoResponse::of).collect(Collectors.toList());
        return getAllList;
    }
    public List<PartyInfoResponse> getMyList(String status, String departure, String destination, String start_date, String condition, String user_id){
        List<PartyInfo> res = findMyList(status, departure, destination, start_date, condition, user_id, user_id);
        List<PartyInfoResponse> getMyList = res.stream().map(PartyInfoResponse::of).collect(Collectors.toList());
        return getMyList;
    }
    /*
    // @Override
    // public List<PartyInfo> findMyList(String status, String search_condition, String order_condition, String value, String user_id, String user_id2) {
    //     if(search_condition == null) search_condition ="";
    //     if(order_condition == null) order_condition ="";
    //     System.out.println("[status:"+status+" order_condition:"+order_condition+ "value :"+value+" user_id:"+ user_id+ " user_id2:"+ user_id2+ "("+user_id.equals(user_id2)+")]");
    //     if(status.equals("now"))
    //     {
    //        if(search_condition.equals("start_date")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyNowListByStartDateOrderByStartDateDesc(user_id,user_id2, value);
    //                 case "review_average_score": return partyInfoRepository.findMyNowListByStartDateOrderByReviewAverageScoreDesc(user_id,user_id2, value);
    //                 default : return partyInfoRepository.findMyNowListNoCondition(user_id,user_id2);
    //             }
    //        }else if(search_condition.equals("department")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyNowListByDepartmentOrderByStartDateDesc(user_id,user_id2, value);
    //                 case "review_average_score": return partyInfoRepository.findMyNowListByDepartmentOrderByReviewAverageScoreDesc(user_id,user_id2, value);
    //                 default : return partyInfoRepository.findMyNowListByDepartmentNoOrder(user_id,user_id2,value);
    //             }
    //        }else if(search_condition.equals("destination")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyNowListByDestinationOrderByStartDateDesc(user_id,user_id2,value);
    //                 case "review_average_score": return partyInfoRepository.findMyNowListByDestinationOrderByReviewAverageScoreDesc(user_id,user_id2,value);
    //                 default : return partyInfoRepository.findMyNowListByDestinationNoOrder(user_id,user_id2,value);
    //             }
    //        }else{
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyNowListByStartDateDesc(user_id,user_id2);
    //                 case "review_average_score": return partyInfoRepository.findMyNowListByReviewAverageScoreDesc(user_id,user_id2);
    //                 default : return partyInfoRepository.findMyNowListNoCondition(user_id,user_id2);
    //             }
    //         }
    //     }
    //     else
    //     {
    //         if(search_condition.equals("start_date")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyPastListByStartDateOrderByStartDateDesc(user_id,user_id2, value);
    //                 case "review_average_score": return partyInfoRepository.findMyPastListByStartDateOrderByReviewAverageScoreDesc(user_id,user_id2, value);
    //                 default : return partyInfoRepository.findMyPastListNoCondition(user_id,user_id2);
    //             }
    //        }else if(search_condition.equals("department")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyPastListByDepartmentOrderByStartDateDesc(user_id,user_id2, value);
    //                 case "review_average_score": return partyInfoRepository.findMyPastListByDepartmentOrderByReviewAverageScoreDesc(user_id,user_id2, value);
    //                 default : return partyInfoRepository.findMyPastListByDepartmentNoOrder(user_id,user_id2,value);
    //             }
    //        }else if(search_condition.equals("destination")){
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyPastListByDestinationOrderByStartDateDesc(user_id,user_id2,value);
    //                 case "review_average_score": return partyInfoRepository.findMyPastListByDestinationOrderByReviewAverageScoreDesc(user_id,user_id2,value);
    //                 default : return partyInfoRepository.findMyPastListByDestinationNoOrder(user_id,user_id2,value);
    //             }
    //        }else{
    //             switch(order_condition){
    //                 case "start_date": return partyInfoRepository.findMyPastListByStartDateDesc(user_id,user_id2);
    //                 case "review_average_score": return partyInfoRepository.findMyPastListByReviewAverageScoreDesc(user_id,user_id2);
    //                 default : return partyInfoRepository.findMyPastListNoCondition(user_id,user_id2);
    //             }
    //         }
    //     }
    // }
 */
    @Override
    public long registMoveInfo(PartyInfo partyInfo) {
        Driver driverInfo = (Driver) partyInfo.getDriver();
        MoveInfo moveInfo = (MoveInfo) partyInfo.getMoveInfo();
        System.out.println(partyInfo);
        long id = partyInfoRepository.save(
            PartyInfo.builder()
                    .curNumberOfParty(partyInfo.getCurNumberOfParty())
                    .maxNumberOfParty(partyInfo.getMaxNumberOfParty())
                    .driver(
                        Driver.builder()
                              .userId(driverInfo.getUserId())
                              .name(driverInfo.getName())
                              .profileImage(driverInfo.getProfileImage())
                              .gender(driverInfo.getGender())
                              .department(driverInfo.getDepartment())
                              .carNumber(driverInfo.getCarNumber())
                              .carKind(driverInfo.getCarKind())
                              .settlementUrl(driverInfo.getSettlementUrl())
                              .reviewInfo(driverInfo.getReviewInfo())
                              .build()
                     )
                     .moveInfo(
                        MoveInfo.builder()
                                .placeOfDeparture(moveInfo.getPlaceOfDeparture())
                                .destination(moveInfo.getDestination())
                                .startDate(moveInfo.getStartDate())
                                .price(moveInfo.getPrice())
                                .distance(moveInfo.getDistance())
                                .build()
                     )
                     .status(partyInfo.getStatus())
                     .build()
        ).getId();

        System.out.println("#운전정보 등록 id: "+ id);
        return id;
    }
    @Override
    public MessageEntity deleteMoveInfo(Long partyId) {
        partyInfoRepository.delete(partyInfoRepository.findById(partyId).orElseThrow(() -> new ApiException(ApiStatus.CANNOT_REMOVE_PARTYINFO)));
        return MessageEntity.of(ApiStatus.REMOVED_PARTYINFO);
    }
    public List<String> findUserIdList(Long partyId){
        List<String> res = partyInfoRepository.findUserIdList(partyId, partyId);
        return res;
    }

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

    public PartyInfoResponse getPost(Long id){
        PartyInfo partyInfo = findById(id);
        return PartyInfoResponse.of(partyInfo);
    }
    public PartyInfo findById(Long partyId){
        //return partyInfoRepository.findById(partyId).orElseGet(PartyInfo::new);
        return partyInfoRepository.findById(partyId).orElseThrow(() -> new ApiException(ApiStatus.NOT_FOUND_PARTYINFO));
    }

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String LocalDateTimetoString(LocalDateTime localDateTime) {
           return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.KOREA).format(localDateTime);
    }

    public MessageEntity addCarpooler(Long partyId, CarPooler carpooler){
        PartyInfo partyInfo = findById(partyId);
        List<CarPooler> carPoolers = partyInfo.getCarPooler();
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
            partyInfo.setCarPooler(carPoolers);
            partyInfoRepository.save(partyInfo);
        }
        catch(Exception e){
            e.getStackTrace();
        }
        return MessageEntity.of(ApiStatus.ADDED_CARPOOLER);

    }
    public MessageEntity removeCarpooler(Long partyId, CarPooler carpooler){
        PartyInfo partyInfo = findById(partyId);
        List<CarPooler> carPoolers = partyInfo.getCarPooler();
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
            partyInfo.setCarPooler(carPoolers);
            partyInfoRepository.save(partyInfo);
        }
        catch(Exception e){

            e.getStackTrace();
        }
        return MessageEntity.of(ApiStatus.REMOVED_CARPOOLER);

    }
}
