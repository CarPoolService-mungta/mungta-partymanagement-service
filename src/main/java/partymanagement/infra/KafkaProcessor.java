package partymanagement.infra;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {

    String PARTY_STATUS_CHANGED = "partyStatusChanged-in-0";
    String PARTY_MEMBER_ACCEPTED = "partyMemberAccept-in-0";
    String PARTY_MEMBER_CANCELED = "partyMemberCanceled-in-0";

    @Input(PARTY_STATUS_CHANGED)
    SubscribableChannel partyStatusChangedTopic();

    @Input(PARTY_MEMBER_ACCEPTED)
    SubscribableChannel partyMemberAcceptedTopic();
    @Input(PARTY_MEMBER_CANCELED)
    SubscribableChannel partyMemberCanceledTopic();

}
