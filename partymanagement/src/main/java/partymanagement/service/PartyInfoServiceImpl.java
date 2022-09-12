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

    @Override
    public List<PartyInfo> findAllList(String status, String search_condition) {
        if(search_condition == null) search_condition ="";
        System.out.println("현재 상태는 "+status+" 현재 정렬 조건은 "+search_condition);
        if(status.equals("now")){
            switch(search_condition){
                case "start_date": return partyInfoRepository.findAllNowListByStartDateDesc();
                case "distance"  : return partyInfoRepository.findAllNowListByDistanceDesc();
                case "review_average_score": return partyInfoRepository.findAllNowListByReviewAverageScoreDesc();
                default : return partyInfoRepository.findAllNowListNoCondition();
            }
            //return search_condition.equals("")?partyInfoRepository.findAllNowListNoCondition() : partyInfoRepository.findAllNowListSort(Sort.by(Sort.Direction.DESC,search_condition));
        }else{
            switch(search_condition){
                case "start_date": return partyInfoRepository.findAllPastListByStartDateDesc();
                case "distance"  : return partyInfoRepository.findAllPastListByDistanceDesc();
                case "review_average_score": return partyInfoRepository.findAllPastListByReviewAverageScoreDesc();
                default : return partyInfoRepository.findAllPastListNoCondition();
            }
            //return search_condition.equals("")?partyInfoRepository.findAllPastListNoCondition() : partyInfoRepository.findAllPastList(search_condition);
        }
    }

    @Override
    public List<PartyInfo> findMyList(String status, String search_condition, String user_id, String user_id2) {
        if(search_condition == null) search_condition ="";
        System.out.println("[status:"+status+" search_condition:"+search_condition+ " user_id:"+ user_id+ " user_id2:"+ user_id2+ "("+user_id.equals(user_id2)+")]");
        if(status.equals("now"))
        {
           // return condition.equals("")?partyInfoRepository.findMyNowListNoCondition(user_id,user_id2) : partyInfoRepository.findMyNowList(user_id,user_id2, condition);
            switch(search_condition){
                case "start_date": return partyInfoRepository.findMyNowListByStartDateDesc(user_id,user_id2);
                case "distance"  : return partyInfoRepository.findMyNowListByDistanceDesc(user_id,user_id2);
                case "review_average_score": return partyInfoRepository.findMyNowListByReviewAverageScoreDesc(user_id,user_id2);
                default : return partyInfoRepository.findMyNowListNoCondition(user_id,user_id2);
            }
        }
        else
        {
            switch(search_condition){
                case "start_date": return partyInfoRepository.findMyPastListByStartDateDesc(user_id,user_id2);
                case "distance"  : return partyInfoRepository.findMyPastListByDistanceDesc(user_id,user_id2);
                case "review_average_score": return partyInfoRepository.findMyPastListByReviewAverageScoreDesc(user_id,user_id2);
                default : return partyInfoRepository.findMyPastListNoCondition(user_id,user_id2);
            }
            //return search_condition.equals("")?partyInfoRepository.findMyPastListNoCondition(user_id,user_id2) : partyInfoRepository.findMyPastList(user_id,user_id2, search_condition);
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
