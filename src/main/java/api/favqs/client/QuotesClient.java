package api.favqs.client;

import api.favqs.helpers.common.UrlHelper;
import api.favqs.models.request.quotes.CreateQuoteRequest;
import api.favqs.models.request.quotes.QuoteListFilter;
import api.favqs.models.response.quotes.ListQuotesResponse;
import api.favqs.models.response.quotes.QuoteResponse;
import api.favqs.routes.QuotesRoutes;
import io.restassured.response.Response;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Quotes api client
 * Implements http methods to work with quotes api controller
 */
public class QuotesClient extends BaseApiClient {
    private QuotesClient(){
    }

    public static QuotesClient quotesApi(){
        return new QuotesClient();
    }

    public CreateQuoteAction addQuote() {
        return new CreateQuoteAction();
    }

    public MarkFavQuoteAction markQuoteFav() {
        return new MarkFavQuoteAction();
    }

    public GetQuoteListAction listQuotes() {
        return new GetQuoteListAction();
    }

    public class CreateQuoteAction {
        private CreateQuoteAction() { }

        /**
         *  Add new quote
         *
         * @param  createQuoteModel quote request model
         * @return                  response from http request
         */
        public Response execute(CreateQuoteRequest createQuoteModel){
            return doPost(QuotesRoutes.Route, createQuoteModel);
        }

        public QuoteResponse executeAs(CreateQuoteRequest createQuoteModel) {
            QuoteResponse result = execute(createQuoteModel).as(QuoteResponse.class);
            assertThat(result.getId()).as("quote has not been created").isNotNull();
            return result;
        }
    }

    public class MarkFavQuoteAction {
        private MarkFavQuoteAction() { }

        /**
         *  Mark quote as favourite
         *
         * @param  isFav   mark as favorite or mark as not favorite
         * @param  quoteId created quoteId
         * @return         response from http request
         */
        public Response execute(boolean isFav, int quoteId){
            String favSegment = isFav ? QuotesRoutes.FavSegment : QuotesRoutes.UnFavSegment;
            String route = UrlHelper.appendSegmentToPath(QuotesRoutes.Route, quoteId);
            String path = UrlHelper.appendSegmentToPath(route, favSegment);

            return doPut(path, isFav);
        }

        public QuoteResponse executeAs(boolean isFav, int quoteId) {
            QuoteResponse result = execute(isFav, quoteId).as(QuoteResponse.class);
            assertThat(result.getUserDetails().isFavorite()).as("quote has not been marked favorite = " + isFav).isEqualTo(isFav);
            return result;
        }
    }

    public class GetQuoteListAction {
        private GetQuoteListAction() { }

        HashMap<String, Object> queryParams = new HashMap<>();

        /**
         *  Query quote list by filter
         *
         * @param  filter  filters for quote list by search value
         * @return         response from http request
         */
        public Response execute(QuoteListFilter filter){
            String path = QuotesRoutes.Route;
            if(filter.getSearchValue() != null)
                queryParams.put("filter", filter.getSearchValue());
            if(filter.getType() != null)
                queryParams.put("type", filter.getType().getValue());
            if(filter.getIsHidden() != null)
                queryParams.put("hidden", filter.getIsHidden() ? 1 : 0);
            if(filter.getIsPrivate() != null)
                queryParams.put("private", filter.getIsPrivate() ? 1 : 0);
            if(filter.getPage() != null)
                queryParams.put("page", filter.getPage());

            return doGet(path, queryParams);
        }

        public ListQuotesResponse executeAs(QuoteListFilter filter) {
            ListQuotesResponse result = execute(filter).as(ListQuotesResponse.class);
            assertThat(result.getQuotes()).as("list of quotes has not been returned").isNotNull();
            return result;
        }
    }
}
