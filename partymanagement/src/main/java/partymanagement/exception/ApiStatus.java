package partymanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ApiStatus {
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -1, "예상치 못한 에러가 발생하였습니다."),
    NOT_FOUND_PARTYINFO(HttpStatus.NOT_FOUND, -800, "해당 카풀파티 정보를 찾을 수 없습니다."),
    INVALID_READ_PARTYINFO(HttpStatus.BAD_REQUEST, -801, "카풀 파티 내용을 볼 권한이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;

    public static ApiStatus of(String message) {
        return Arrays.stream(ApiStatus.values())
                .filter(apiStatus -> apiStatus.getMessage().equals(message))
                .findFirst()
                .orElse(null);
    }
}
