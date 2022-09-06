package partymanagement.domain.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

import org.springframework.beans.BeanUtils;

@Embeddable
@Data
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
}
