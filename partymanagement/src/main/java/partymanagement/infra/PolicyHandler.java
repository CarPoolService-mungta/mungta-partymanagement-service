package partymanagement.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import partymanagement.config.kafka.KafkaProcessor;
import partymanagement.domain.*;
import partymanagement.domain.event.ClosedParty;
import partymanagement.domain.repository.PartyInfoRepository;

// @Service
// @Transactional
public class PolicyHandler {

    // @Autowired
    // PartyInfoRepository partyInfoRepository;

    // @StreamListener(KafkaProcessor.INPUT)
    // public void whatever(@Payload String eventString) {}

    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverClosedParty_ClosedParty(
    //     @Payload ClosedParty closedParty
    // ) {
    //     if (!closedParty.validate()) return;
    //     ClosedParty event = closedParty;
    //     System.out.println(
    //         "\n\n##### listener ClosedParty : " + closedParty.toJson() + "\n\n"
    //     );
    //     // Sample Logic //

    // }
    // // keep

}
