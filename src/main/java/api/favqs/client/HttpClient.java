package api.favqs.client;

import java.util.HashMap;

public interface HttpClient {
    <T> T doPost(String path, Object body);
    <T> T doPut(String path, Object body);
    <T> T doGet(String path, HashMap<String, Object> params);
    <T> T doDelete(String path, HashMap<String, Object> params);
    void SetRequestSpec();
}
