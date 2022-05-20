package api.favqs.models.response.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ListQuotesResponse {
    @JsonProperty("page")
    private String page;
    @JsonProperty("last_page")
    private boolean last_page;
    @JsonProperty("quotes")
    private QuoteResponse[] quotes;
}
