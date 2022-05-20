package api.favqs.models.request.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteRequest {
    @JsonProperty("author")
    private String author;
    @JsonProperty("body")
    private String body;
}
