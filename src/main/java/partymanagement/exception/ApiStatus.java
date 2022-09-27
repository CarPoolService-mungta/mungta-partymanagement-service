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
    INVALID_READ_PARTYINFO(HttpStatus.BAD_REQUEST, -801, "카풀 파티 내용을 볼 권한이 없습니다."),
    CANNOT_ADD_CARPOOLER(HttpStatus.NOT_MODIFIED, -802, "카풀러가 해당 파티에 있어 추가가 불가합니다."),
    ADDED_CARPOOLER(HttpStatus.ACCEPTED, -803, "카풀러가 해당 파티에 추가되었습니다."),
    CANNOT_REMOVE_CARPOOLER(HttpStatus.NOT_MODIFIED, -804, "카풀러가 해당 파티에 없어 삭제가 불가합니다. 관리자 확인이 필요합니다."),
    REMOVED_CARPOOLER(HttpStatus.ACCEPTED, -805, "카풀러가 해당 파티에서 삭제되었습니다."),
    CANNOT_REMOVE_PARTYINFO(HttpStatus.ACCEPTED, -806, "해당 파티가 삭제 불가합니다."),
    REMOVED_PARTYINFO(HttpStatus.ACCEPTED, -807, "해당 파티가 삭제되었습니다."),
    CANNOT_FOUND_CARPOOLER(HttpStatus.INTERNAL_SERVER_ERROR, -810, "카풀러를 찾을 수 없습니다."),
    EXCEED_MAX_NUMBER(HttpStatus.INTERNAL_SERVER_ERROR, -810, "파티에 인원이 초과하였습니다."),
    MINUS_MEMBER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -810, "파티 인원 취소 중 문제가 발생하였습니다."),
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
