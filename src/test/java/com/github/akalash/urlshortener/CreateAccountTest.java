package com.github.akalash.urlshortener;

import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for create account process
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class CreateAccountTest extends AbstractIntegrationTest {

    @Test
    public void should_createAccount_because_everythingIsOk() throws Exception {
        mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"accountId\":\"testAccountId\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.password", notNullValue()))
                .andExpect(jsonPath("$.description", is("account is opened")));
    }

    @Test(dependsOnMethods = "should_createAccount_because_everythingIsOk")
    public void should_returnError_because_accountAlreadyExists() throws Exception {
        mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"accountId\":\"testAccountId\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.password", nullValue()))
                .andExpect(jsonPath("$.description", is("account already exist")));
    }

}