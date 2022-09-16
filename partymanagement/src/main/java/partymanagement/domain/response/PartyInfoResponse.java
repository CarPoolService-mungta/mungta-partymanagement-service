package partymanagement.domain.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import partymanagement.domain.vo.*;
import partymanagement.domain.PartyInfo;
import partymanagement.domain.enumeration.*;

@EqualsAndHashCode
@Data
@Builder
public class PartyInfoResponse {

    private Long id;
    private int curNumberOfParty;
    private int maxNumberOfParty;
    private Driver driver;
    private MoveInfo moveInfo;
    private List<CarPooler> carPooler;
    private PartyStatus status;

    public static PartyInfoResponse of(PartyInfo partyInfo){
        return PartyInfoResponse.builder()
                    .id(partyInfo.getId())
                    .curNumberOfParty(partyInfo.getCurNumberOfParty())
                    .maxNumberOfParty(partyInfo.getMaxNumberOfParty())
                    .driver(partyInfo.getDriver())
                    .moveInfo(partyInfo.getMoveInfo())
                    .carPooler(partyInfo.getCarPooler())
                    .status(partyInfo.getStatus())
                    .build();
    }

}
