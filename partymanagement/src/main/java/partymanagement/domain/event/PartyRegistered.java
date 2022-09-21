package partymanagement.domain.event;

import lombok.Getter;
import partymanagement.domain.PartyInfo;
import partymanagement.infra.AbstractEvent;

@Getter
public class PartyRegistered extends AbstractEvent {

    private Long partyId;
    private String driverId;
    private String driverName;
    private int maxNumberOfParty;

    public PartyRegistered(PartyInfo partyInfo) {
        super();
        this.partyId = partyInfo.getId();
        this.driverId=partyInfo.getDriver().getUserId();
        this.driverName=partyInfo.getDriver().getName();
        this.maxNumberOfParty=partyInfo.getMaxNumberOfParty();
    }

    @Override
    public String toString() {
        return "PartyCreated{" +
                "eventType='" + eventType + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", partyId='" + partyId + '\'' +
                ", driverId='" + driverId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", maxNumberOfParty='" + maxNumberOfParty + '\'' +
                '}';
    }

}
