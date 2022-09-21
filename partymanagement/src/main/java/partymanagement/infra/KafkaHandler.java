package partymanagement.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import partymanagement.domain.event.MatchAccepted;
import partymanagement.domain.event.MatchCancelled;
import partymanagement.domain.event.PartyStatusChanged;
import partymanagement.service.PartyInfoService;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaHandler {

    private final PartyInfoService partyInfoService;

    @StreamListener(KafkaProcessor.PARTY_STATUS_CHANGED)
    public void wheneverPartyStatusChanged_changePartyStatus(@Payload PartyStatusChanged partyStatusChanged){
        if(!partyStatusChanged.validate())
            return;

        partyInfoService.changePartyStatus(partyStatusChanged);

    }

    @StreamListener(KafkaProcessor.PARTY_MEMBER_ACCEPTED)
    public void wheneverPartyMemberAccepted_registerCarpooler(@Payload MatchAccepted matchAccepted){
        if(!matchAccepted.validate())
            return;

        partyInfoService.acceptCarpooler(matchAccepted);
    }

    @StreamListener(KafkaProcessor.PARTY_MEMBER_CANCELED)
    public void wheneverPartyMemberCanceled_cancelCarpooler(@Payload MatchCancelled matchCancelled){
        if(!matchCancelled.validate())
            return;

        partyInfoService.cancelCarpoolerApply(matchCancelled);
    }

}
