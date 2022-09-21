package partymanagement.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import partymanagement.PartyManagementApplication;
import partymanagement.domain.repository.PartyInfoRepository;
import partymanagement.domain.vo.*;
import partymanagement.domain.enumeration.*;
import partymanagement.exception.ApiException;
import partymanagement.exception.ApiStatus;

@Entity
@Table(name = "PartyInfo_table")
@Data
@NoArgsConstructor
public class PartyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int curNumberOfParty;
    private int maxNumberOfParty;
    @Embedded
    private Driver driver;

    @Embedded
    private MoveInfo moveInfo;

    @ElementCollection
    private List<CarPooler> carPoolers;

    private PartyStatus status;

    public static PartyInfoRepository repository() {
        PartyInfoRepository partyInfoRepository = PartyManagementApplication.applicationContext.getBean(
            PartyInfoRepository.class
        );
        return partyInfoRepository;
    }

    //Carpooler는 미리 되는일은 x
    @Builder
    public PartyInfo(int curNumberOfParty, int maxNumberOfParty, Driver driver, MoveInfo moveInfo, PartyStatus status){
        setCurNumberOfParty(curNumberOfParty);
        setMaxNumberOfParty(maxNumberOfParty);
        setDriver(driver);
        setMoveInfo(moveInfo);
        setStatus(status);
    }
    @Builder
    public PartyInfo(int curNumberOfParty, int maxNumberOfParty, Driver driver, MoveInfo moveInfo, PartyStatus status, List<CarPooler> carPoolers){
        setCurNumberOfParty(curNumberOfParty);
        setMaxNumberOfParty(maxNumberOfParty);
        setDriver(driver);
        setMoveInfo(moveInfo);
        setStatus(status);
        setCarPoolers(carPoolers);
    }

    public void addCarpooler(CarPooler carPooler){
        if(carPoolers==null){
            carPoolers= new ArrayList<>();
        }
        CarPooler alreadyCarpooler = carPoolers.stream().filter(o->o.getUserId().equals(carPooler)).findFirst().orElse(null);
        if(!Objects.isNull(alreadyCarpooler)){
            new ApiException(ApiStatus.CANNOT_ADD_CARPOOLER);
        }
        carPoolers.add(carPooler);
    }
    public void selectRole() {}
}
