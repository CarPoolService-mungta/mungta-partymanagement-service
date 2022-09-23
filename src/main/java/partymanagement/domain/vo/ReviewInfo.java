package partymanagement.domain.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.BeanUtils;

@Embeddable
@Data
@NoArgsConstructor
public class ReviewInfo {

    private double reviewAverageScore;

    private String recentComment;

    @Builder
    public ReviewInfo(double reviewAverageScore, String recentComment){
        setReviewAverageScore(reviewAverageScore);
        setRecentComment(recentComment);
    }
}
