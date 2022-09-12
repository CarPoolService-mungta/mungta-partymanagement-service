package partymanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import partymanagement.domain.PartyInfo;
import partymanagement.domain.repository.PartyInfoRepository;
import partymanagement.domain.vo.CarPooler;
import partymanagement.domain.vo.CarPooler;
import partymanagement.domain.vo.Driver;
import partymanagement.domain.vo.MoveInfo;

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
    public List<PartyInfo> findAllList(String status, String search_condition, String order_condition, String value) {
        if(search_condition == null) search_condition ="";
        if(order_condition == null) order_condition ="";
        if(status.equals("now"))
        {
           if(search_condition.equals("start_date")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findAllNowListByStartDateOrderByStartDateDesc(value);
                    case "review_average_score": return partyInfoRepository.findAllNowListByStartDateOrderByReviewAverageScoreDesc(value);
                    default : return partyInfoRepository.findAllNowListNoCondition();
                }
           }else if(search_condition.equals("department")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findAllNowListByDepartmentOrderByStartDateDesc(value);
                    case "review_average_score": return partyInfoRepository.findAllNowListByDepartmentOrderByReviewAverageScoreDesc(value);
                    default : return partyInfoRepository.findAllNowListByDepartmentNoOrder(value);
                }
           }else if(search_condition.equals("destination")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findAllNowListByDestinationOrderByStartDateDesc(value);
                    case "review_average_score": return partyInfoRepository.findAllNowListByDestinationOrderByReviewAverageScoreDesc(value);
                    default : return partyInfoRepository.findAllNowListByDestinationNoOrder(value);
                }
           }else{
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findAllNowListByStartDateDesc();
                    case "review_average_score": return partyInfoRepository.findAllNowListByReviewAverageScoreDesc();
                    default : return partyInfoRepository.findAllNowListNoCondition();
                }
            }
        }
        else{
            switch(order_condition){
                case "start_date": return partyInfoRepository.findAllPastListByStartDateDesc();
                case "review_average_score": return partyInfoRepository.findAllPastListByReviewAverageScoreDesc();
                default : return partyInfoRepository.findAllPastListNoCondition();
            }
        }
    }
    @Override
    public List<PartyInfo> findMyList(String status, String search_condition, String order_condition, String value, String user_id, String user_id2) {
        if(search_condition == null) search_condition ="";
        if(order_condition == null) order_condition ="";
        System.out.println("[status:"+status+" order_condition:"+order_condition+ " user_id:"+ user_id+ " user_id2:"+ user_id2+ "("+user_id.equals(user_id2)+")]");
        if(status.equals("now"))
        {
           if(search_condition.equals("start_date")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyNowListByStartDateOrderByStartDateDesc(user_id,user_id2, value);
                    case "review_average_score": return partyInfoRepository.findMyNowListByStartDateOrderByReviewAverageScoreDesc(user_id,user_id2, value);
                    default : return partyInfoRepository.findMyNowListNoCondition(user_id,user_id2);
                }
           }else if(search_condition.equals("department")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyNowListByDepartmentOrderByStartDateDesc(user_id,user_id2, value);
                    case "review_average_score": return partyInfoRepository.findMyNowListByDepartmentOrderByReviewAverageScoreDesc(user_id,user_id2, value);
                    default : return partyInfoRepository.findMyNowListByDepartmentNoOrder(user_id,user_id2,value);
                }
           }else if(search_condition.equals("destination")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyNowListByDestinationOrderByStartDateDesc(user_id,user_id2,value);
                    case "review_average_score": return partyInfoRepository.findMyNowListByDestinationOrderByReviewAverageScoreDesc(user_id,user_id2,value);
                    default : return partyInfoRepository.findMyNowListByDestinationNoOrder(user_id,user_id2,value);
                }
           }else{
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyNowListByStartDateDesc(user_id,user_id2);
                    case "review_average_score": return partyInfoRepository.findMyNowListByReviewAverageScoreDesc(user_id,user_id2);
                    default : return partyInfoRepository.findMyNowListNoCondition(user_id,user_id2);
                }
            }
        }
        else
        {
            if(search_condition.equals("start_date")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyPastListByStartDateOrderByStartDateDesc(user_id,user_id2, value);
                    case "review_average_score": return partyInfoRepository.findMyPastListByStartDateOrderByReviewAverageScoreDesc(user_id,user_id2, value);
                    default : return partyInfoRepository.findMyPastListNoCondition(user_id,user_id2);
                }
           }else if(search_condition.equals("department")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyPastListByDepartmentOrderByStartDateDesc(user_id,user_id2, value);
                    case "review_average_score": return partyInfoRepository.findMyPastListByDepartmentOrderByReviewAverageScoreDesc(user_id,user_id2, value);
                    default : return partyInfoRepository.findMyPastListByDepartmentNoOrder(user_id,user_id2,value);
                }
           }else if(search_condition.equals("destination")){
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyPastListByDestinationOrderByStartDateDesc(user_id,user_id2,value);
                    case "review_average_score": return partyInfoRepository.findMyPastListByDestinationOrderByReviewAverageScoreDesc(user_id,user_id2,value);
                    default : return partyInfoRepository.findMyPastListByDestinationNoOrder(user_id,user_id2,value);
                }
           }else{
                switch(order_condition){
                    case "start_date": return partyInfoRepository.findMyPastListByStartDateDesc(user_id,user_id2);
                    case "review_average_score": return partyInfoRepository.findMyPastListByReviewAverageScoreDesc(user_id,user_id2);
                    default : return partyInfoRepository.findMyPastListNoCondition(user_id,user_id2);
                }
            }
        }
    }

    @Override
    public long registMoveInfo(PartyInfo partyInfo) {
        Driver driverInfo = (Driver) partyInfo.getDriver();
        MoveInfo moveInfo = (MoveInfo) partyInfo.getMoveInfo();
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

    public PartyInfo findById(Long partyId){
        return partyInfoRepository.findById(partyId).orElseGet(PartyInfo::new);
    }

}
