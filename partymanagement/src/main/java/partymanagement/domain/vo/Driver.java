package partymanagement.domain.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.BeanUtils;

@Embeddable
@Data
@NoArgsConstructor
public class Driver {

    private String userId;

    private String name;

    private String profileImage;

    private String gender;

    private String department;

    private String carNumber;

    private String carKind;

    private String settlementUrl;

    @Embedded
    private ReviewInfo reviewInfo;

    @Builder
    public Driver(String userId, String name, String profileImage, String gender, String department, String carNumber, String carKind, String settlementUrl, ReviewInfo reviewInfo){
        setUserId(userId);
        setName(name);
        setProfileImage(profileImage);
        setGender(gender);
        setDepartment(department);
        setCarNumber(carNumber);
        setCarKind(carKind);
        setSettlementUrl(settlementUrl);
        setReviewInfo(reviewInfo);
    }
}
