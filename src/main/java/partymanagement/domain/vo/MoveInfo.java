package partymanagement.domain.vo;

import java.time.LocalDateTime;
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
public class MoveInfo {

    private String placeOfDeparture;

    private String destination;

    private LocalDateTime startDate; //Date로 바꿔야할 수 있음

    private String price;

    private String distance;

    @Builder
    public MoveInfo(String placeOfDeparture, String destination, LocalDateTime startDate, String price, String distance){
        setPlaceOfDeparture(placeOfDeparture);
        setDestination(destination);
        setStartDate(startDate);
        setPrice(price);
        setDistance(distance);
    }
}
