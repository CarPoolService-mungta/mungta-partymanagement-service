package partymanagement.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import partymanagement.domain.*;
import partymanagement.domain.request.CarpoolerInfoRequest;
import partymanagement.domain.response.PartyAccusationResponse;
import partymanagement.domain.response.PartyInfoResponse;
import partymanagement.domain.vo.CarPooler;
import partymanagement.service.PartyInfoService;

import partymanagement.exception.MessageEntity;

//@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping(value = "/api/party-management/partyInfos")
//@RequestMapping(value = "/api/partyInfos")
@Transactional
public class PartyInfoController {


    @Autowired
    PartyInfoService partyInfoService;

    // private final HandlerUtils handler = HandlerUtils.getInstance();

    @Operation(summary = "카풀 상세 정보")
    @GetMapping("/carpool-info")
    public ResponseEntity<PartyInfoResponse> getPost(@RequestParam(required = true) Long id) {

        PartyInfoResponse response = partyInfoService.getPost(id);
        return  ResponseEntity.ok(response);
    }

    @Operation(summary = "현재 카풀리스트 조회")
    @GetMapping("/carpool-now-list")
    public ResponseEntity<List<PartyInfoResponse>> getPostList(@RequestParam(required = false) String departure,@RequestParam(required = false) String destination, @RequestParam(required = false) String start_date, @RequestParam(required = false) String condition) {
        System.out.println("departure, destination, start_date, order:"+departure+","+ destination+","+ start_date+","+condition);
        List<PartyInfoResponse> response =partyInfoService.getAllList("now",departure, destination, start_date, condition);
        return  ResponseEntity.ok(response);
    }
    @Operation(summary = "과거 카풀리스트 조회")
    @GetMapping("/carpool-past-list")
    public ResponseEntity<List<PartyInfoResponse>> getPastPostList(@RequestParam(required = false) String departure,@RequestParam(required = false) String destination, @RequestParam(required = false) String start_date, @RequestParam(required = false) String condition) {
        System.out.println("departure, destination, start_date, order:"+departure+","+ destination+","+ start_date+","+condition);
        List<PartyInfoResponse> response =partyInfoService.getAllList("past",departure, destination, start_date, condition);
        return  ResponseEntity.ok(response);
    }
    @Operation(summary = "회원의 현재 진행 중인 카풀리스트 조회")
    @GetMapping("/carpool-now-my-list")
    public ResponseEntity<List<PartyInfoResponse>> getPostMyList(@RequestParam(required = false) String departure,@RequestParam(required = false) String destination, @RequestParam(required = false) String start_date, @RequestParam(required = false) String condition, @RequestParam(required = true) String user_id) {
        System.out.println("departure, destination, start_date, order, user_id:"+departure+","+ destination+","+ start_date+","+condition+","+user_id);
        List<PartyInfoResponse> response =partyInfoService.getMyList("now",departure, destination, start_date, condition, user_id);
        return  ResponseEntity.ok(response);
    }
    @Operation(summary = "회원의 지난 내역 카풀리스트 조회")
    @GetMapping("/carpool-past-my-list")
    public ResponseEntity<List<PartyInfoResponse>> getPastPostMyList(@RequestParam(required = false) String departure,@RequestParam(required = false) String destination, @RequestParam(required = false) String start_date, @RequestParam(required = false) String condition, @RequestParam(required = true) String user_id) {
        System.out.println("departure, destination, start_date, order, user_id:"+departure+","+ destination+","+ start_date+","+condition+","+user_id);
        List<PartyInfoResponse> response =partyInfoService.getMyList("past",departure, destination, start_date, condition, user_id);
        return  ResponseEntity.ok(response);
    }

    @Operation(summary = "운전정보 등록")
    @PostMapping(value="/post-moveinfo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> postMoveInfo(@RequestBody PartyInfo payload){
        long id = partyInfoService.registMoveInfo(payload);
        return ResponseEntity.created(URI.create("/api/carpool-info/"+id)).build();
    }
    @Operation(summary = "운전정보 삭제")
    @DeleteMapping(value="/delete-moveinfo")
    public ResponseEntity<MessageEntity> deleteMoveInfo(@RequestParam(required = true) Long partyId){
        MessageEntity response = partyInfoService.deleteMoveInfo(partyId);
        return ResponseEntity.ok(response);
    }
    @GetMapping(value="/carpool-summary-info")
    public ResponseEntity<PartyAccusationResponse> getSummaryInfo(@RequestParam(required = true) Long partyId){

        PartyAccusationResponse response = partyInfoService.getSummaryInfo(partyId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "카풀러 추가")
    @PostMapping(value="/post-carpooler-info", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageEntity> addCarPoolerInfo(@RequestBody CarpoolerInfoRequest payload){
        System.out.println(payload);
        CarPooler carpoolerInfo =  CarPooler.builder()
                                            .userId(payload.getUserId())
                                            .name(payload.getName())
                                            .profileImage(payload.getProfileImage())
                                            .department(payload.getDepartment())
                                            .carpoolerCheck(payload.getCarpoolerCheck())
                                            .driverCheck(payload.getDriverCheck())
                                            .carpoolingStatus(payload.getCarpoolingStatus())
                                            .startDate(payload.getStartDate())
                                            .build();
        MessageEntity response = partyInfoService.addCarpooler(payload.getPartyId(), carpoolerInfo);
        return ResponseEntity.ok(response);
        //return ResponseEntity.created(URI.create("/api/carpool-info/"+id)).build();
    }
    @Operation(summary = "카풀러 제거")
    @PostMapping(value="/delete-carpooler-info", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageEntity> deleteCarPoolerInfo(@RequestBody CarpoolerInfoRequest payload){
        System.out.println(payload);
        CarPooler carpoolerInfo =  CarPooler.builder()
                                            .userId(payload.getUserId())
                                            .name(payload.getName())
                                            .profileImage(payload.getProfileImage())
                                            .department(payload.getDepartment())
                                            .carpoolerCheck(payload.getCarpoolerCheck())
                                            .driverCheck(payload.getDriverCheck())
                                            .carpoolingStatus(payload.getCarpoolingStatus())
                                            .startDate(payload.getStartDate())
                                            .build();
        MessageEntity response = partyInfoService.removeCarpooler(payload.getPartyId(), carpoolerInfo);
        return ResponseEntity.ok(response);
        //return ResponseEntity.created(URI.create("/api/carpool-info/"+id)).build();
    }
    // @GetMapping("/carpool-past-list")
    // public ResponseEntity<Map<String, Object>> getPastPostList(@RequestParam(required = false) String search_condition,@RequestParam(required = false) String order_condition, @RequestParam(required = false) String value) {
    //     return handler.handleSuccess(partyInfoService.findAllList("past",search_condition, order_condition, value));
    // }
    // @GetMapping("/carpool-now-my-list")
    // public ResponseEntity<Map<String, Object>> getPostMyList(@RequestParam(required = false) String search_condition,@RequestParam(required = false) String order_condition, @RequestParam(required = false) String value, @RequestParam(required = true) String user_id) {
    //     System.out.println("search_condition, order_condition, value, user_id:"+search_condition+","+ order_condition+","+ value+","+user_id);
    //     return handler.handleSuccess(partyInfoService.findMyList("now",search_condition, order_condition, value, user_id, user_id));
    // }

    // @GetMapping("/carpool-past-my-list")
    // public ResponseEntity<Map<String, Object>> getPastPostMyList(@RequestParam(required = false) String search_condition,@RequestParam(required = false) String order_condition, @RequestParam(required = false) String value, @RequestParam(required = true) String user_id) {
    //     return handler.handleSuccess(partyInfoService.findMyList("past",search_condition, order_condition, value, user_id, user_id));
    // }


    // @RequestMapping(value = "/{userId}/selectrole", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    // public PartyInfo selectRole(@PathVariable(value = "userId") String userId, HttpServletRequest request, HttpServletResponse response ) throws Exception {
    //     System.out.println("##### /partyInfo/selectRole  called #####");
    //     // Optional<PartyInfo> optionalPartyInfo = partyInfoRepository.findByDriverUserId(
    //     //     userId
    //     // );

    //     // optionalPartyInfo.orElseThrow(() -> new Exception("No Entity Found"));
    //     // PartyInfo partyInfo = optionalPartyInfo.get();
    //     // partyInfo.selectRole();

    //     // partyInfoRepository.save(partyInfo);
    //     return null;// partyInfo;
    // }


    // @GetMapping("/partymanagement-find-by-startdatetime")
    // public ResponseEntity<Map<String, Object>> findByMoveInfoStartDateTime(@RequestParam String startDates){

    //     return handler.handleSuccess(partyInfoService.findByMoveInfoStartDate(startDates));
    // }

    // @GetMapping("/partymanagement-find-by-startdate")
    // public ResponseEntity<Map<String, Object>> findByMoveInfoStartDate(@RequestParam String startDates){
    //     // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //     // LocalDateTime dateTime = LocalDateTime.parse(startDates, formatter);
    //     // error 처리해도 되는디
    //     return handler.handleSuccess(partyInfoService.findByMoveInfoStartDate(startDates));
    // }

}
