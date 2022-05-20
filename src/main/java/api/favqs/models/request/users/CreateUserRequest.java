package api.favqs.models.request.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateUserRequest {
    @JsonProperty("user")
    public UserModel user;
}
