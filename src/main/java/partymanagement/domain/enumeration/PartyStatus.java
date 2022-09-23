package partymanagement.domain.enumeration;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum PartyStatus {
    OPEN,
    FULL,
    CANCELED,
    STARTED,
    CLOSED,
    REJECT,//파티 생성이 파티매칭에 되지 않았을 경우
}
