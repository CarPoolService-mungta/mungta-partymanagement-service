package partymanagement.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    List<PartyInfo> findByDriverUserId(String userId);

    List<PartyInfo> findByDriver(Driver driver);

    List<PartyInfo> findByCarPoolerUserId(String userId);

    //이것들로 하니까 id를 가져오는데?
    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.start_date  LIKE  CONCAT(:times,'%')", nativeQuery = true)
    List<PartyInfo> findByMoveInfoStartDate(@Param("times") String times);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status < 2 ORDER BY :search_condition DESC", nativeQuery = true)
    List<PartyInfo> findAllNowList(@Param("search_condition") String search_condition);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status < 2 ", nativeQuery = true)
    List<PartyInfo> findAllNowListNoCondition();

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status > 2 ORDER BY :search_condition DESC", nativeQuery = true)
    List<PartyInfo> findAllPastList(@Param("search_condition") String search_condition);

    @Query(value ="SELECT p.* FROM party_info_table p WHERE p.status > 2 ", nativeQuery = true)
    List<PartyInfo> findAllPastListNoCondition();

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
