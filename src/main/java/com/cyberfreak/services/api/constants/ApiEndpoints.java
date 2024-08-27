package com.cyberfreak.services.api.constants;

public final class ApiEndpoints {

    public static final String BASE_PATH = "/api";
    public static final String VERSION_1 = "/v1";
    public static final String PAGE_CONTENT_PATH = BASE_PATH + VERSION_1 + "/page-contents";
    public static final String APPLICATION_PATH = BASE_PATH + VERSION_1 + "/applications";

    private ApiEndpoints() {
        throw new IllegalStateException("Utility class");
    }

}
