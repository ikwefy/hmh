package api.favqs.models.request.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateQuoteRequest {
    @JsonProperty("quote")
    private QuoteRequest quote;
}
