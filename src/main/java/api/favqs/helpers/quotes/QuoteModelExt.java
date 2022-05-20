package api.favqs.helpers.quotes;

import api.favqs.helpers.common.RandomGenerator;
import api.favqs.models.request.quotes.CreateQuoteRequest;
import api.favqs.models.request.quotes.QuoteRequest;

/**
 * Generate quote request model
 */
public class QuoteModelExt {
    public static CreateQuoteRequest initQuoteCreateModel(){
        CreateQuoteRequest createQuote = new CreateQuoteRequest();
        QuoteRequest quote = new QuoteRequest();
        quote.setAuthor(RandomGenerator.generateRandomName("Author"));
        quote.setBody(RandomGenerator.generateRandomName("body"));
        createQuote.setQuote(quote);

        return  createQuote;
    }
}
