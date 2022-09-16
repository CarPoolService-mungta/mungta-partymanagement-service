package partymanagement.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import partymanagement.domain.*;
import partymanagement.domain.vo.Driver;

// @RepositoryRestResource(
//     collectionResourceRel = "partyInfos",
//     path = "partyInfos"
// )
// public interface PartyInfoRepository
//     extends PagingAndSortingRepository<PartyInfo, Long> {

//     Optional<PartyInfo> findByUserId(String userId);}

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface PartyInfoRepository extends JpaRepository<PartyInfo, Long>{




    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.start_date  LIKE  CONCAT(:times,'%')", nativeQuery = true)
    List<PartyInfo> findByMoveInfoStartDate(@Param("times") String times);


    //# 현재 전체 카풀리스트를 검색 (OPEN, FULL)
    //native query를 사용하는 경우 sort 불가
    // @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') ORDER BY :search_condition DESC", nativeQuery = true)
    // List<PartyInfo> findAllNowList();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByStartDateDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByReviewAverageScoreDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListNoCondition();

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.start_date  LIKE  CONCAT(:times,'%') ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByStartDateOrderByStartDateDesc(@Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.department  LIKE  CONCAT(:place,'%') ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByDepartmentOrderByStartDateDesc(@Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.destination  LIKE  CONCAT(:place,'%') ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByDestinationOrderByStartDateDesc(@Param("place") String place);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.start_date  LIKE  CONCAT(:times,'%') ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByStartDateOrderByReviewAverageScoreDesc(@Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.department  LIKE  CONCAT(:place,'%') ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByDepartmentOrderByReviewAverageScoreDesc(@Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.destination  LIKE  CONCAT(:place,'%') ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByDestinationOrderByReviewAverageScoreDesc(@Param("place") String place);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.start_date  LIKE  CONCAT(:times,'%') ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByStartDateNoOrder(@Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.department  LIKE  CONCAT(:place,'%') ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByDepartmentNoOrder(@Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1') AND p.destination  LIKE  CONCAT(:place,'%') ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByDestinationNoOrder(@Param("place") String place);


    //# 종료된 전체 카풀리스트를 검색 (CLOSED)
    //native query를 사용하는 경우 sort 불가
    // @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') ORDER BY :search_condition DESC", nativeQuery = true)
    // List<PartyInfo> findAllPastList(@Param("search_condition") String search_condition);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllPastListByStartDateDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') ORDER BY distance ASC", nativeQuery = true)
    List<PartyInfo> findAllPastListByDistanceDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllPastListByReviewAverageScoreDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findAllPastListNoCondition();

    //# 현재 진행 중인 카풀 중 본인이 포함된 카풀리스트를 검색 (OPEN, FULL, STARTED)
    /* native query를 사용하는 경우 sort 불가*/
    // @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND c.user_id = :user_id2 ORDER BY :search_condition DESC", nativeQuery = true)
    // List<PartyInfo> findMyNowList(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("search_condition") String search_condition);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListNoCondition(@Param("user_id") String user_id, @Param("user_id2") String user_id2);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.start_date  LIKE  CONCAT(:times,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.start_date  LIKE  CONCAT(:times,'%') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByStartDateOrderByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.department  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.department  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByDepartmentOrderByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.destination  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.destination  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByDestinationOrderByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.start_date  LIKE  CONCAT(:times,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.start_date  LIKE  CONCAT(:times,'%') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByStartDateOrderByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.department  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.department  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByDepartmentOrderByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.destination  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.destination  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByDestinationOrderByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.start_date  LIKE  CONCAT(:times,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.start_date  LIKE  CONCAT(:times,'%') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByStartDateNoOrder(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.department  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.department  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByDepartmentNoOrder(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('0','1','3') AND p.destination  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('0','1','3') AND p.destination  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyNowListByDestinationNoOrder(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);

    //# 현재 완료된 카풀 중 본인이 포함된 카풀리스트를 검색 (CLOSED)
    /* native query를 사용하는 경우 sort 불가*/
    // @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND c.user_id = :user_id2 ORDER BY :search_condition DESC", nativeQuery = true)
    // List<PartyInfo> findMyPastList(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("search_condition") String search_condition);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND c.user_id = :user_id2 ORDER BY distance ASC", nativeQuery = true)
    List<PartyInfo> findMyPastListByDistanceDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListNoCondition(@Param("user_id") String user_id, @Param("user_id2") String user_id2);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.start_date  LIKE  CONCAT(:times,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.start_date  LIKE  CONCAT(:times,'%') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByStartDateOrderByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.department  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.department  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByDepartmentOrderByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.destination  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.destination  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY start_date DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByDestinationOrderByStartDateDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.start_date  LIKE  CONCAT(:times,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.start_date  LIKE  CONCAT(:times,'%') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByStartDateOrderByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.department  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.department  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByDepartmentOrderByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.destination  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.destination  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByDestinationOrderByReviewAverageScoreDesc(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.start_date  LIKE  CONCAT(:times,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.start_date  LIKE  CONCAT(:times,'%') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByStartDateNoOrder(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("times") String times);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.department  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.department  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByDepartmentNoOrder(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status in ('4') AND p.destination  LIKE  CONCAT(:place,'%') AND p.user_id = :user_id UNION SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE p.status in ('4') AND p.destination  LIKE  CONCAT(:place,'%') AND c.user_id = :user_id2 ORDER BY id DESC", nativeQuery = true)
    List<PartyInfo> findMyPastListByDestinationNoOrder(@Param("user_id") String user_id, @Param("user_id2") String user_id2, @Param("place") String place);


/*
    //잘 안먹어서.. 그냥 쿼리로 함
    @Query(value ="SELECT p.* FROM party_info_table p ORDER BY p.start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllOrderByMoveInfoStartDateDesc();
    @Query(value ="SELECT p.* FROM party_info_table p ORDER BY p.distance DESC", nativeQuery = true)
    List<PartyInfo> findAllOrderByMoveInfoDistanceDesc();
    @Query(value ="SELECT p.* FROM party_info_table p ORDER BY p.review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllOrderByDriverReviewInfoReviewAverageScoreDesc();

    //전체 참가 가능 리스트만 (OPEN, FULL만)
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status < 2 ORDER BY p.start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByStartDateDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status < 2 ORDER BY p.distance DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByDistanceDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status < 2 ORDER BY p.review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllNowListByReviewAverageScoreDesc();

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status > 2 ORDER BY p.start_date DESC", nativeQuery = true)
    List<PartyInfo> findAllPastListByStartDateDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status > 2 ORDER BY p.distance DESC", nativeQuery = true)
    List<PartyInfo> findAllPastListByDistanceDesc();
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status > 2 ORDER BY p.review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findAllPastListByReviewAverageScoreDesc();

    //Drvier로 참여한
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.user_id = :user_id ORDER BY p.start_date DESC;", nativeQuery = true)
    List<PartyInfo> findByDriverUserIdOrderByMoveInfoStartDateDesc(@Param("user_id") String user_id);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.user_id = :user_id ORDER BY p.distance DESC", nativeQuery = true)
    List<PartyInfo> findByDriverUserIdOrderByMoveInfoDistanceDesc(@Param("user_id") String user_id);
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.user_id = :user_id ORDER BY p.review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findByDriverUserIdOrderByDriverReviewInfoReviewAverageScoreDesc(@Param("user_id") String user_id);

    //Carpooler로 참여한
    @Query(value ="SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE c.user_id = :user_id ORDER BY p.start_date DESC;", nativeQuery = true)
    List<PartyInfo> findByCarPoolerUserIdOrderByMoveInfoStartDateDesc(@Param("user_id") String user_id);
    @Query(value ="SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE c.user_id = :user_id ORDER BY p.distance DESC", nativeQuery = true)
    List<PartyInfo> findByCarPoolerUserIdOrderByMoveInfoDistanceDesc(@Param("user_id") String user_id);
    @Query(value ="SELECT p.* FROM party_info_table p LEFT OUTER JOIN party_info_car_pooler c ON p.id = c.party_info_id WHERE c.user_id = :user_id ORDER BY p.review_average_score DESC", nativeQuery = true)
    List<PartyInfo> findByCarPoolerUserIdOrderByDriverReviewInfoReviewAverageScoreDesc(@Param("user_id") String user_id);
*/
}
