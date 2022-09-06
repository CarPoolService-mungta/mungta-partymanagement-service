package partymanagement.service;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import partymanagement.domain.PartyInfo;
import partymanagement.domain.repository.PartyInfoRepository;

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
    
}
