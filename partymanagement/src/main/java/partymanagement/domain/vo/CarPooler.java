package partymanagement.domain.vo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import partymanagement.domain.enumeration.CarpoolingStatus;
import partymanagement.domain.enumeration.PayCheck;

import org.springframework.beans.BeanUtils;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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

    private LocalDateTime startDate;

    @Builder
    public CarPooler(String userId, String name, String profileImage, String department, PayCheck carpoolerCheck, PayCheck driverCheck, CarpoolingStatus carpoolingStatus, LocalDateTime startDate){
        setUserId(userId);
        setName(name);
        setProfileImage(profileImage);
        setDepartment(department);
        setCarpoolerCheck(carpoolerCheck);
        setDriverCheck(driverCheck);
        setCarpoolingStatus(carpoolingStatus);
        setStartDate(startDate);
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
