package partymanagement.domain.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import partymanagement.domain.PartyInfo;

@Data
@Builder
public class PartyAccusationResponse {

    private long partyId;
    private String placeOfDeparture;
    private String destination;
    private String startDate;
    private List<String> userIds;


}
