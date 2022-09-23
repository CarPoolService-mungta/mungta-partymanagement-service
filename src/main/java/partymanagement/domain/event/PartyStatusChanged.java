package partymanagement.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import partymanagement.domain.enumeration.PartyStatus;
import partymanagement.infra.AbstractEvent;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartyStatusChanged extends AbstractEvent {

  private Long partyInfoId;
  private PartyStatus partyStatus;
  private PartyStatus pastPartyStatus;

}
