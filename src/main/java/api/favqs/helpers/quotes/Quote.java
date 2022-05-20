package api.favqs.helpers.quotes;

import api.favqs.client.QuotesClient;
import api.favqs.models.request.quotes.CreateQuoteRequest;
import api.favqs.models.request.quotes.QuoteListFilter;
import api.favqs.models.response.ErrorResponse;
import api.favqs.models.response.quotes.ListQuotesResponse;
import api.favqs.models.response.quotes.QuoteResponse;

/**
 * Single responsibility Quote class
 * Store logic relates to work with quote client
 */
public class Quote {
    public CreateQuoteRequest createModel;
    public QuoteResponse detailsModel;
    public ListQuotesResponse listQuotesModel;
    public ErrorResponse errorDetails;
    private static QuotesClient client;

    public Quote(QuotesClient client) {
        this.client = client;
        this.createModel = QuoteModelExt.initQuoteCreateModel();
    }

    public Quote Create(){
        detailsModel = client.addQuote().executeAs(createModel);

        return  this;
    }

    public Quote MarkAsFav(boolean isFav){
        detailsModel = client.markQuoteFav().executeAs(isFav, detailsModel.getId());

        return  this;
    }

    public Quote MarkAsFavWithError(boolean isFav, int quoteId){
        errorDetails = client.markQuoteFav().execute(isFav, quoteId).as(ErrorResponse.class);

        return  this;
    }

    public Quote GetListQuotes(QuoteListFilter filter){
        listQuotesModel = client.listQuotes().executeAs(filter);

        return  this;
    }
}
