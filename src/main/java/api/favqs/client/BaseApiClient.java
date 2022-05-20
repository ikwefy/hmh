package api.favqs.client;

import api.favqs.helpers.common.ApiPropertiesHelper;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;

import java.util.HashMap;

/**
 * Implementation of http client methods and RestAssured initialization
 * GET / PUT / POST / DELETE
 */
public class BaseApiClient implements HttpClient{
    /**
     * RequestSpecBuilder for base http client
     * Initialize new instance each time when called
     */
    private RequestSpecBuilder reqSpecBuilder;

    /**
     * Thread safe user token value
     * Should be initialized once per user session
     */
    protected static String userTokenValue;

    public BaseApiClient() {
        RestAssured.baseURI = ApiPropertiesHelper.getBaseUri();
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
                    return mapper;
                }
        ));
    }

    public <T> T doPost(String path, Object body) {
        SetRequestSpec();

        if (body != null) {
            reqSpecBuilder.setBody(body);
        }

        return (T) RestAssured
                .given()
                .spec(reqSpecBuilder.build())
                .post(path)
                .then().extract().response();
    }

    public <T> T doPut(String path, Object body) {
        SetRequestSpec();

        if (body != null) {
            reqSpecBuilder.setBody(body);
        }

        return (T) RestAssured
                .given()
                .spec(reqSpecBuilder.build())
                .put(path)
                .then().extract().response();
    }

    public <T> T doGet(String path, HashMap<String, Object> params) {
        SetRequestSpec();

        if (params != null) {
            reqSpecBuilder.addQueryParams(params);
        }

        return (T) RestAssured
                .given()
                .spec(reqSpecBuilder.build())
                .get(path)
                .then().extract().response();
    }

    public <T> T doDelete(String path, HashMap<String, Object> params) {
        SetRequestSpec();

        if (params != null) {
            reqSpecBuilder.addQueryParams(params);
        }

        return (T) RestAssured
                .given()
                .spec(reqSpecBuilder.build())
                .delete(path)
                .then().extract().response();
    }

    /**
     * Set request authorization and user session per http request
     */
    public void SetRequestSpec(){
        reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.addHeader("Authorization", "Token token=" + ApiPropertiesHelper.getApiKey());
        reqSpecBuilder.setContentType(ContentType.JSON);

        if(userTokenValue != null)
            reqSpecBuilder.addHeader("User-Token", userTokenValue);

    }
}
