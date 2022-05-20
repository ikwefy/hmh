package api.favqs.helpers.common;

/**
 * Store helper logic relates to url building
 */
public class UrlHelper {
    public static String appendSegmentToPath(String path, Object segment) {
        if (path == null || path.isEmpty()) {
            return "/" + segment;
        }

        if (path.charAt(path.length() - 1) == '/') {
            return path + segment;
        }

        return path + "/" + segment;
    }
}
