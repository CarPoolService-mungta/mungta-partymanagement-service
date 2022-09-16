package partymanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MessageEntity {
    private final int status;
    private final String message;

    private MessageEntity(ApiStatus apiStatus) {
        this.status = apiStatus.getStatus();
        this.message = apiStatus.getMessage();
    }

    public static MessageEntity of(ApiStatus apiStatus) {
        return new MessageEntity(apiStatus);
    }

}
