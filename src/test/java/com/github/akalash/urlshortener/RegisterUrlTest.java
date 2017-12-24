package com.github.akalash.urlshortener;

import com.github.akalash.urlshortener.services.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for register url process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class RegisterUrlTest extends AbstractIntegrationTest {

    @Autowired
    CreateAccountService createAccountService;

    @Test
    public void should_registerUrl_because_everythingIsOk() throws Exception {
        String accountId = "testRegister";
        String password = createAccountService.createAccount(accountId).getBody();

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", basicAuth(accountId, password))
                .content("{\"url\":\"http://google.ru\", \"redirectType\":301}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortUrl", notNullValue()));
    }

    @Test
    public void should_returnUnauthorized_when_requestWithoutAuthorizationHeader() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"url\":\"http://google.ru\", \"redirectType\":301}"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}