package partymanagement.domain.vo;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import partymanagement.domain.PartyInfo;
import partymanagement.domain.enumeration.CarpoolingStatus;
import partymanagement.domain.enumeration.PayCheck;

@Data
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CarPooler {

    private String userId;

    private String name;

    private String department;

    @Enumerated(EnumType.STRING)
    private PayCheck carpoolerCheck;

    @Enumerated(EnumType.STRING)
    private PayCheck driverCheck;

    @Enumerated(EnumType.STRING)
    private CarpoolingStatus carpoolingStatus;

    @Builder
    public CarPooler(String userId, String name, String profileImage, String department, PayCheck carpoolerCheck, PayCheck driverCheck, CarpoolingStatus carpoolingStatus, LocalDateTime startDate){
        setUserId(userId);
        setName(name);
        setDepartment(department);
        setCarpoolerCheck(carpoolerCheck);
        setDriverCheck(driverCheck);
        setCarpoolingStatus(carpoolingStatus);
    }
    @Override
    public boolean equals(Object object){
        CarPooler carPooler = (CarPooler) object;
        if (carPooler.userId.equals(this.userId)){
            return true;
        }
        return false;
    }
}
