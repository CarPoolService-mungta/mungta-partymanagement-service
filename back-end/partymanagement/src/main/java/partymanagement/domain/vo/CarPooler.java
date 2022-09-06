package partymanagement.domain.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import partymanagement.domain.enumeration.CarpoolingStatus;
import partymanagement.domain.enumeration.PayCheck;

import org.springframework.beans.BeanUtils;

@Embeddable
@Data
public class CarPooler {

    private String userId;

    private String name;

    private String profileImage;

    private String department;

    @Enumerated(EnumType.STRING)
    private PayCheck carpoolerCheck;

    @Enumerated(EnumType.STRING)
    private PayCheck driverCheck;

    @Enumerated(EnumType.STRING)
    private CarpoolingStatus carpoolingStatus;

    private Date startDate;
}
