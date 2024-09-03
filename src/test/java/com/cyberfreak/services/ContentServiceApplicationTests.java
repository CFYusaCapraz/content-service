package com.cyberfreak.services;

import com.cyberfreak.services.main.ContentServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ContentServiceApplication.class)
@ActiveProfiles(profiles = "base_test")
public class ContentServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
