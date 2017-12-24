package com.github.akalash.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

/**
 * Super class for other integration tests
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {
    @Autowired
    MockMvc mockMvc;

    protected String basicAuth(String accountId, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((accountId + ":" + password).getBytes());
    }
}
