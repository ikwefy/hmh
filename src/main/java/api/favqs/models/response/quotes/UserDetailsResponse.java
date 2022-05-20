package api.favqs.models.response.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDetailsResponse {
    @JsonProperty("downvote")
    private boolean downvote;
    @JsonProperty("favorite")
    private boolean favorite;
    @JsonProperty("hidden")
    private boolean hidden;
    @JsonProperty("upvote")
    private boolean upvote;
}
