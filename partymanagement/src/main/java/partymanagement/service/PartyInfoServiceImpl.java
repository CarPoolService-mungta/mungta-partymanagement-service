package partymanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<PartyInfo> findByDriverUserId(String userId) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<PartyInfo> findByDriver(Driver driver) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public PartyInfo findByMoveInfoStartDate(LocalDateTime times) {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // public List<PartyInfo> findAllNowList(String condition) {
    //     switch(condition){
    //         case "startdate":
    //             return partyInfoRepository.findA();
    //         case "distance":
    //             return partyInfoRepository.findAllByStatusOrderByMoveInfoDistanceDesc();
    //         case "reviewscore":
    //             return partyInfoRepository.findAllByStatusOrderByDriverReviewInfoReviewAverageScoreDesc();
    //         default:
    //             return null;
    //     }
    // }
//이거 그냥 status랑 condition 받아서 하나로 하자.
    @Override
    public List<PartyInfo> findAllList(String status, String condition) {

        if(status.equals("now")){
            return condition.equals("")?partyInfoRepository.findAllNowListNoCondition() : partyInfoRepository.findAllNowList(condition);
        }else{
            return condition.equals("")?partyInfoRepository.findAllPastListNoCondition() : partyInfoRepository.findAllPastList(condition);
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

}
