package partymanagement.domain.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import partymanagement.domain.enumeration.CarpoolingStatus;
import partymanagement.domain.enumeration.PayCheck;

@Data
@Builder
@ToString
public class CarpoolerInfoRequest {

    private Long partyId;
    private String userId;
    private String name;
    private String profileImage;
    private String department;
    private PayCheck carpoolerCheck;
    private PayCheck driverCheck;
    private CarpoolingStatus carpoolingStatus;
    private LocalDateTime startDate;

}
