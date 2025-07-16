package eighteenthhometask.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseModel {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    Boolean isActive;
    @JsonProperty("created_date")
    String createdDate;
}
