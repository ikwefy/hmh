package api.favqs.models.response.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateUserResponse {
    @JsonProperty("User-Token")
    private String userToken;
    @JsonProperty("login")
    private String login;
}
