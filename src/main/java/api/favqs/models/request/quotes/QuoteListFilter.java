package api.favqs.models.request.quotes;

import api.favqs.enums.QuoteFilterType;
import lombok.Data;

@Data
public class QuoteListFilter {
    private String searchValue;
    private QuoteFilterType type;
    private Boolean isHidden;
    private Boolean isPrivate;
    private Integer page;

    public QuoteListFilter() {
        this.searchValue = null;
        this.type = null;
        this.isHidden = null;
        this.isPrivate = null;
        this.page = null;
    }

    public QuoteListFilter(String filter, QuoteFilterType type) {
        this.searchValue = filter;
        this.type = type;
        this.isHidden = null;
        this.isPrivate = null;
        this.page = null;
    }

    public QuoteListFilter(String filter) {
        this.searchValue = filter;
        this.type = null;
        this.isHidden = null;
        this.isPrivate = null;
        this.page = null;
    }

    public QuoteListFilter(Boolean isHidden) {
        this.searchValue = null;
        this.type = null;
        this.isHidden = isHidden;
        this.isPrivate = null;
        this.page = null;
    }

    public QuoteListFilter(int pageNumber) {
        this.searchValue = null;
        this.type = null;
        this.isHidden = null;
        this.isPrivate = null;
        this.page = pageNumber;
    }
}
