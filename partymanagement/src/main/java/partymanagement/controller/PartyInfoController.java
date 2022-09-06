package partymanagement.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import partymanagement.domain.*;
import partymanagement.domain.repository.PartyInfoRepository;
import partymanagement.infra.HandlerUtils;
import partymanagement.service.PartyInfoService;


//@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping(value = "/api/party-management/partyInfos")
//@RequestMapping(value = "/api/partyInfos")
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
    //@RequestBody HashMap<String,Object> param로도 가능
    @PostMapping(value="/post-moveinfo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> postMoveInfo(@RequestBody PartyInfo payload){
        long id = partyInfoService.registMoveInfo(payload);
        return handler.handleSuccess(id);
    }

}
