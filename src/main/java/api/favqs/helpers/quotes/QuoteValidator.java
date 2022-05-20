package api.favqs.helpers.quotes;
;
import api.favqs.helpers.common.UrlHelper;
import api.favqs.models.response.quotes.QuoteResponse;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Validates quote business logic
 */
public class QuoteValidator {
    public static void validateCreatedQuoteInTheList(@NotNull Quote source){
        QuoteResponse quote = Arrays.stream(source.listQuotesModel.getQuotes())
                .filter(n->n.getId() == source.detailsModel.getId())
                .findFirst()
                .get();

        String expectedUrl = UrlHelper.appendSegmentToPath(source.detailsModel.getAuthor(), source.detailsModel.getId() + "-" + source.detailsModel.getBody());
        assertThat(quote.getTags()).as("tags should be empty").isEmpty();
        assertThat(quote.getBody()).as("body is wrong").isEqualTo(source.detailsModel.getBody());
        assertThat(quote.getAuthor()).as("author is wrong").isEqualTo(source.detailsModel.getAuthor());
        assertThat(quote.isDialogue()).as("dialog is wrong").isFalse();
        assertThat(quote.getDownvotesCount()).as("down votes count is wrong").isZero();
        assertThat(quote.getUpvotesCount()).as("up votes count is wrong").isZero();
        assertThat(quote.isPrivate()).as("isPrivate wrong").isFalse();
        assertThat(quote.getUrl()).as("url is wrong").contains(expectedUrl.toLowerCase());
        assertThat(quote.getUserDetails().isFavorite()).as("isFavorite wrong").isEqualTo(source.detailsModel.getUserDetails().isFavorite());
        assertThat(quote.getUserDetails().isDownvote()).as("isDownvote wrong").isFalse();
        assertThat(quote.getUserDetails().isUpvote()).as("isUpvote wrong").isFalse();
        assertThat(quote.getUserDetails().isHidden()).as("isHidden wrong").isFalse();
    }

    public static void validateError(@NotNull Quote source, int errorCode, String errorMessage){
        assertThat(source.errorDetails.getErrorCode())
                .as("error is wrong")
                .isEqualTo(errorCode);
        assertThat(source.errorDetails.getMessage())
                .as("error is wrong")
                .isEqualTo(errorMessage);
    }

    public static void validateNotFoundQuotes(@NotNull Quote source, String errorMessage){
        QuoteResponse quote = Arrays.stream(source.listQuotesModel.getQuotes())
                .findFirst()
                .get();
        assertThat(quote.getBody())
                .as("quotes list response is wrong")
                .isEqualTo("No quotes found");
    }

    public static void validateNumberPerPage(@NotNull Quote source){
        assertThat(Arrays.stream(source.listQuotesModel.getQuotes()).count())
                .as("number of quotes per page is wrong")
                .isEqualTo(25);
    }

    public static void validateIsHiddenFilter(@NotNull Quote source, boolean isHidden) {
        long allListCount = Arrays.stream(source.listQuotesModel.getQuotes()).count();
        long isHiddenListCount = Arrays.stream(source.listQuotesModel.getQuotes())
                .filter(n -> n.getUserDetails().isHidden() == isHidden)
                .count();

        assertThat(allListCount)
                .as("isHidden query returns wrong quote list")
                .isEqualTo(isHiddenListCount);
    }

    public static void validateTagsFilter(@NotNull Quote source, String tag) {
        long allListCount = Arrays.stream(source.listQuotesModel.getQuotes()).count();
        long tagsListCount = Arrays.stream(source.listQuotesModel.getQuotes())
                .filter(n -> Arrays.stream(n.getTags()).anyMatch(tag::contains)).count();

        assertThat(allListCount)
                .as("tags query returns wrong quote list")
                .isEqualTo(tagsListCount);
    }
}
