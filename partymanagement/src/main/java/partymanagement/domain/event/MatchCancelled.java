package partymanagement.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import partymanagement.domain.enumeration.CarpoolingStatus;
import partymanagement.infra.AbstractEvent;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MatchCancelled extends AbstractEvent {

    private Long partyInfoId;
    private String userId;
    private CarpoolingStatus matchStatus;


}
