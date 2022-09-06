package partymanagement.domain.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Embeddable
@Data
public class ReviewInfo {

    private Long reviewAverageScore;

    private String recentComment;
}
