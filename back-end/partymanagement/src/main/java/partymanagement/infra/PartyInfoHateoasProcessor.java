package partymanagement.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import partymanagement.domain.*;

@Component
public class PartyInfoHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PartyInfo>> {

    @Override
    public EntityModel<PartyInfo> process(EntityModel<PartyInfo> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/selectrole")
                .withRel("selectrole")
        );

        return model;
    }
}
