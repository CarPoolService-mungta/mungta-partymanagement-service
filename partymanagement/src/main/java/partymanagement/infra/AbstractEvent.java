package partymanagement.infra;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class AbstractEvent {


    protected final String eventType;
    protected final String timestamp;

    public AbstractEvent() {
        this.eventType = this.getClass().getSimpleName();
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public boolean validate(){
        return getEventType().equals(getClass().getSimpleName());
    }

}
