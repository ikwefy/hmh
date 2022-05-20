package api.favqs.client;

import api.favqs.helpers.quotes.Quote;
import api.favqs.helpers.users.User;

/**
 * Favqs api client
 * Initialize and work with all favqs api controllers
 */
public class FavqsApiClient {
    public User user;
    public Quote quote;
    public FavqsApiClient() {
        this.user = new User(UsersClient.usersApi()).Create();
        this.quote = new Quote(QuotesClient.quotesApi());
    }
}
