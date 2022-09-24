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
import partymanagement.domain.event.PartyRegistered;
import partymanagement.domain.event.PartyStatusChanged;
import partymanagement.service.PartyInfoService;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaHandler {

    private final PartyInfoService partyInfoService;
    private final KafkaProducer kafkaProducer;

    @StreamListener(KafkaProcessor.PARTY_STATUS_CHANGED)
    public void wheneverPartyStatusChanged_changePartyStatus(@Payload PartyStatusChanged partyStatusChanged){
        if(!partyStatusChanged.validate())
            return;
        try{
            partyInfoService.changePartyStatus(partyStatusChanged);
        }catch (Exception e){
            kafkaProducer.send("partyStatusChangedReject-out",partyStatusChanged);
        }

    }

    @StreamListener(KafkaProcessor.PARTY_MEMBER_ACCEPTED)
    public void wheneverPartyMemberAccepted_registerCarpooler(@Payload MatchAccepted matchAccepted){
        if(!matchAccepted.validate())
            return;
        try{
            partyInfoService.acceptCarpooler(matchAccepted);
        }catch (Exception e){
            log.error("error: ", e);
            e.printStackTrace();
            kafkaProducer.send("partyMemberAcceptReject-out",matchAccepted);
        }

    }

    @StreamListener(KafkaProcessor.PARTY_MEMBER_CANCELED)
    public void wheneverPartyMemberCanceled_cancelCarpooler(@Payload MatchCancelled matchCancelled){
        if(!matchCancelled.validate())
            return;
        try{
            partyInfoService.cancelCarpoolerApply(matchCancelled);
        }catch (Exception e){
            kafkaProducer.send("partyMemberCanceledReject-out",matchCancelled);
        }

    }

    @StreamListener(KafkaProcessor.PARTY_MEMBER_CANCELED)
    public void wheneverPartyRegisteredReject_rejectParty(@Payload PartyRegistered partyRegistered){
        if(!partyRegistered.validate())
            return;

        partyInfoService.registMoveInfoRollback(partyRegistered);

    }

}
