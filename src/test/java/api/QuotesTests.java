package api;

import api.favqs.client.FavqsApiClient;
import api.favqs.enums.QuoteFilterType;
import api.favqs.helpers.quotes.Quote;
import api.favqs.helpers.quotes.QuoteValidator;
import api.favqs.helpers.users.User;
import api.favqs.models.request.quotes.QuoteListFilter;
import org.testng.annotations.*;

public class QuotesTests extends TestBase {
    public FavqsApiClient favqsApi;
    public Quote quote;
    public User user;

    /**
     * Following method creates user and quote
     * as a precondition for current suite
     */
    @BeforeSuite
    public void createUserAndQuote(){
        favqsApi = new FavqsApiClient();
        user = favqsApi.user;
        quote = favqsApi.quote.Create();
    }

    /**
     * Following test verifies that quote
     * can be marked as fav and unfav using endpoints:
     * PUT /api/quotes/:quote_id/fav
     * PUT /api/quotes/:quote_id/unfav
     */
    @Test(dataProvider = "getMarkFavoriteCases")
    public void checkMarkQuoteAsFavorite(Boolean isFavorite) {
        if(!isFavorite)
            quote.MarkAsFav(true);

        quote
                .MarkAsFav(isFavorite)
                .GetListQuotes(new QuoteListFilter(quote.createModel.getQuote().getBody()));
        QuoteValidator.validateCreatedQuoteInTheList(quote);
    }

    /**
     * Following test verifies error response
     * for invalid quoteId and expects 40 error
     * code according to requirements https://favqs.com/api
     */
    @Test
    public void checkFavQuoteNotFoundError() {
        quote.MarkAsFavWithError(true, 0);
        QuoteValidator.validateError(quote, 40, "Quote not found.");
    }

    /**
     * Following test queries GET /api/quotes endpoint
     * and expects created quote in response
     */
    @Test(dataProvider = "getFilterCases")
    public void checkListQuoteFilters(QuoteListFilter filter) {
        quote.GetListQuotes(filter);
        QuoteValidator.validateCreatedQuoteInTheList(quote);
    }

    /**
     * Following test queries GET /api/quotes endpoint
     * and expects hidden quote in response
     */
    @Test(dataProvider = "getIsHiddenFilterCases")
    public void checkIsHiddenFilter(QuoteListFilter filter) {
        quote.GetListQuotes(filter);
        QuoteValidator.validateIsHiddenFilter(quote, filter.getIsHidden());
    }

    /**
     * Following test queries GET /api/quotes endpoint
     * and expects quote with exact tags in response
     */
    @Test(dataProvider = "getTagsFilterCases")
    public void checkTagsFilter(QuoteListFilter filter) {
        quote.GetListQuotes(filter);
        QuoteValidator.validateTagsFilter(quote, filter.getSearchValue());
    }

    /**
     * Following test queries GET /api/quotes endpoint
     * and expects error "No quote found" in response
     */
    @Test(dataProvider = "getNotFoundFilterCases")
    public void checkNotFoundListQuotes(QuoteListFilter filter){
        quote.GetListQuotes(filter);
        QuoteValidator.validateNotFoundQuotes(quote, "No quotes found");
    }

    /**
     * Following test switches pages of GET /api/quotes endpoint
     * and expects 25 quotes per page in responses
     */
    @Test
    public void checkQuotesPager(){
        for (int page = 1; page <= 5; page++)
            QuoteValidator.validateNumberPerPage(quote.GetListQuotes(new QuoteListFilter(page)));
    }

    @DataProvider()
    public Object[][] getMarkFavoriteCases() {
        Object[] data = new Boolean[]{
                true,
                false,
        };

        return  getDataProvider(data);
    }

    @DataProvider()
    public Object[][] getFilterCases() {
        Object[] data = new QuoteListFilter[]{
                new QuoteListFilter(user.createModel.getUser().getLogin(), QuoteFilterType.USER),
                new QuoteListFilter(quote.createModel.getQuote().getAuthor(), QuoteFilterType.AUTHOR),
                new QuoteListFilter(quote.createModel.getQuote().getBody())
        };

        return  getDataProvider(data);
    }

    @DataProvider()
    public Object[][] getNotFoundFilterCases() {

        Object[] data = new QuoteListFilter[]{
                new QuoteListFilter(user.createModel.getUser().getEmail(), QuoteFilterType.USER),
                new QuoteListFilter(quote.createModel.getQuote().getAuthor(), QuoteFilterType.TAG),
                new QuoteListFilter(user.createModel.getUser().getLogin(), QuoteFilterType.TAG),
                new QuoteListFilter(quote.createModel.getQuote().getAuthor(), QuoteFilterType.USER),
                new QuoteListFilter(quote.createModel.getQuote().getBody(), QuoteFilterType.AUTHOR)
        };

        return getDataProvider(data);
    }

    @DataProvider()
    public Object[][] getIsHiddenFilterCases() {

        Object[] data = new QuoteListFilter[]{
                new QuoteListFilter(true),
                new QuoteListFilter(false)
        };

        return getDataProvider(data);
    }

    @DataProvider()
    public Object[][] getTagsFilterCases() {

        Object[] data = new QuoteListFilter[]{
                new QuoteListFilter("beauty", QuoteFilterType.TAG),
                new QuoteListFilter("whistle-blower", QuoteFilterType.TAG)
        };

        return getDataProvider(data);
    }
}
