package com.cyberfreak.services.api.constants;

public final class ApiPaths {

    public static final String PAGE_CONTENT_ID = "pageContentId";
    public static final String PAGE_NAME = "pageName";
    public static final String ADD_CONTENT_ITEMS_PATH = "/{pageContentId}/content-items";
    public static final String CREATE_WITH_CONTENT_ITEMS_PATH = "/with-content-items";
    public static final String CREATE_WITH_EXISTING_CONTENT_ITEMS_PATH = "/with-existing-content-items";
    public static final String PAGE_CONTENT_BY_NAME_PATH = "/{pageName}";

    private ApiPaths() {
        throw new IllegalStateException("Utility class");
    }
}
