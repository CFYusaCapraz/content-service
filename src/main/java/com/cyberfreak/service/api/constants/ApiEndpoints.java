package com.cyberfreak.service.api.constants;

public final class ApiEndpoints {

    public static final String BASE_PATH = "/api";
    public static final String VERSION_1 = "/v1";
    public static final String PAGE_CONTENT_PATH = BASE_PATH + VERSION_1 + "/page-contents";

    private ApiEndpoints() {
        throw new IllegalStateException("Utility class");
    }

}
