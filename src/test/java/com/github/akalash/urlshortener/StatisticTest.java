package com.github.akalash.urlshortener;

import com.github.akalash.urlshortener.domain.Account;
import com.github.akalash.urlshortener.repository.AccountRepository;
import com.github.akalash.urlshortener.repository.StatisticRepository;
import com.github.akalash.urlshortener.services.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for get statistic process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class StatisticTest extends AbstractIntegrationTest {

    @Autowired
    CreateAccountService createAccountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StatisticRepository statisticRepository;

    @Test
    public void should_returnCorrectStatistic_because_everythingIsOk() throws Exception {
        String accountId = "testStatistic1";
        String password = createAccountService.createAccount(accountId).getBody();
        Account account = accountRepository.findByUsername(accountId);

        statisticRepository.upsert("google.ru", account.getId());
        statisticRepository.upsert("google.ru", account.getId());

        mockMvc.perform(get("/statistic/" + accountId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", basicAuth(accountId, password)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['google.ru']", is(2)));
    }

    @Test
    public void should_returnUnauthorized_when_accountAndAuthAccountIsDifferent() throws Exception {
        String accountId = "testStatistic2";
        String password = createAccountService.createAccount(accountId).getBody();

        mockMvc.perform(get("/statistic/otherAccount")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", basicAuth(accountId, password)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}