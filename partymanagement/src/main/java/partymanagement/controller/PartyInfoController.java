package partymanagement.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import partymanagement.domain.*;
import partymanagement.domain.repository.PartyInfoRepository;
import partymanagement.infra.HandlerUtils;
import partymanagement.service.PartyInfoService;


//@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
//@RequestMapping(value = "/api/party-management/partyInfos")
@RequestMapping(value = "/api/partyInfos")
@Transactional
public class PartyInfoController {

    @Autowired
    PartyInfoRepository partyInfoRepository;

    @Autowired
    PartyInfoService partyInfoService;

    private final HandlerUtils handler = HandlerUtils.getInstance();

    @RequestMapping(value = "/{userId}/selectrole", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public PartyInfo selectRole(@PathVariable(value = "userId") String userId, HttpServletRequest request, HttpServletResponse response ) throws Exception {
        System.out.println("##### /partyInfo/selectRole  called #####");
        // Optional<PartyInfo> optionalPartyInfo = partyInfoRepository.findByDriverUserId(
        //     userId
        // );

        // optionalPartyInfo.orElseThrow(() -> new Exception("No Entity Found"));
        // PartyInfo partyInfo = optionalPartyInfo.get();
        // partyInfo.selectRole();

        // partyInfoRepository.save(partyInfo);
        return null;// partyInfo;
    }


    @GetMapping("/partymanagement-find-by-startdate")
    public ResponseEntity<Map<String, Object>> findByMoveInfoStartDate(@RequestParam String startDates){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(startDates, formatter);

        System.out.println("[start]#####################################################findByMoveInfo");
        System.out.println("PartyInfoController.findByMoveInfoStartDate()");
        System.out.println("startDates:"+startDates);
        System.out.println("dateTime:"+dateTime);
        System.out.println(partyInfoRepository.findByMoveInfoStartDate(startDates));
        System.out.println("[end]#####################################################findByMoveInfo");
        return handler.handleSuccess(partyInfoRepository.findByMoveInfoStartDate(startDates));
    }

    @GetMapping("/carpool-now-list")
    public ResponseEntity<Map<String, Object>> getPostList(@RequestParam(required = false) String condition) {
        return handler.handleSuccess(partyInfoService.findAllList("now",condition));
    }
    @GetMapping("/carpool-past-list")
    public ResponseEntity<Map<String, Object>> getPastPostList(@RequestParam(required = false) String condition) {
        return handler.handleSuccess(partyInfoService.findAllList("past",condition));
    }
    @PostMapping("/post-moveinfo")
    public ResponseEntity<Map<String, Object>> postMoveInfo(@RequestBody PartyInfo param){
        System.out.println("param : " + param);
        String res = "어어 왔어.";
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println(res+param);
        return handler.handleSuccess(param);
    }
    @PostMapping("/post-moveinfo2")
    public ResponseEntity<Map<String, Object>> postMoveInfo2(@RequestBody HashMap<String,Object> param){
        System.out.println("param2 : " + param);
        for(String k : param.keySet()){
            System.out.println(k+"="+param.get(k));
        }
        String res = "어어 왔어2.";
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println(res+param);
        return handler.handleSuccess(param);
    }
    @PostMapping("/post-moveinfo3")
    public ResponseEntity<Map<String, Object>> postMoveInfo3(PartyInfo partyInfo){
        System.out.println("param3 : " + partyInfo);
        String res = "어어 왔어3.";
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println(res+partyInfo);
        return handler.handleSuccess(partyInfo);
    }

    @PostMapping("/post-moveinfo4")
    public ResponseEntity<Map<String, Object>> postMoveInfo4(HttpServletResponse response){
        System.out.println("param4 : " + response.getHeaderNames());
        String res = "어어 왔어4.";
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println(res+response);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("data","dd");
        return handler.handleSuccess(map);
    }

    @PostMapping(value ="/post-moveinfo5", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> postMoveInfo5(@RequestBody HashMap<String,Object> param){
        System.out.println("param2 : " + param);
        for(String k : param.keySet()){
            System.out.println(k+"="+param.get(k));
        }
        String res = "어어 왔어2.";
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println("#######################################");
        System.out.println(res+param);
        return handler.handleSuccess(param);
    }

}
