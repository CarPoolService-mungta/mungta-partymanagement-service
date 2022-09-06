package partymanagement.domain.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Embeddable
@Data
public class MoveInfo {

    private String placeOfDeparture;

    private String destination;

    private Date startDate;

    private String price;

    private String distance;
}
