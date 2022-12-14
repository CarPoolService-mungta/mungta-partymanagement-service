package partymanagement.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import partymanagement.domain.response.UserResponse;

import java.util.List;

@FeignClient(name = "user", url = "${api.url.user}")
public interface UserServiceClient {

    @GetMapping(path = "")
    List<UserResponse> getUserList(@RequestParam List<String> userIds);

}
