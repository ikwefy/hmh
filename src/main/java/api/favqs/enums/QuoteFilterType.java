package api.favqs.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QuoteFilterType {
    AUTHOR("author", 1),

    TAG("tag", 2),

    USER("user", 3);

    private String value;
    private final Integer id;

    QuoteFilterType(String value, Integer id) {
        this.value = value;
        this.id = id;
    }

    @Override
    public String toString() {
        return value;
    }

    public Integer getId() {
        return id;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


    public static Integer getIdByValue(String value) {
        return Integer.parseInt(String.valueOf(findByKey(value).getId()));
    }

    public static QuoteFilterType findByKey(String value) {
        System.out.println(value);
        QuoteFilterType[] testEnums = QuoteFilterType.values();
        for (QuoteFilterType testEnum : testEnums) {
            if (testEnum.getValue().contains(value)) {
                return testEnum;
            }
        }
        return null;
    }
}
