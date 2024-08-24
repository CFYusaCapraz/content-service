package com.cyberfreak.service.api.constants;

public final class ApiPaths {

    public static final String PAGE_CONTENT_ID = "pageContentId";
    public static final String ADD_CONTENT_ITEMS_PATH = "/{pageContentId}/content-items";
    public static final String CREATE_WITH_CONTENT_ITEMS_PATH = "/with-content-items";
    public static final String CREATE_WITH_EXISTING_CONTENT_ITEMS_PATH = "/with-existing-content-items";

    private ApiPaths() {
        throw new IllegalStateException("Utility class");
    }
}
