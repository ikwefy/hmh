package api.favqs.models.response.quotes;

import api.favqs.models.request.quotes.QuoteRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteResponse extends QuoteRequest {
    @JsonProperty("author_permalink")
    private String authorPermalink;
    @JsonProperty("dialogue")
    private boolean dialogue;
    @JsonProperty("downvotes_count")
    private int downvotesCount;
    @JsonProperty("favorites_count")
    private int favoritesCount;
    @JsonProperty("id")
    private int id;
    @JsonProperty("private")
    private boolean isPrivate;
    @JsonProperty("upvotes_count")
    private int upvotesCount;
    @JsonProperty("url")
    private String url;
    @JsonProperty("source")
    private String source;
    @JsonProperty("context")
    private String context;
    @JsonProperty("tags")
    private String[] tags;
    @JsonProperty("user_details")
    private UserDetailsResponse userDetails;

}
