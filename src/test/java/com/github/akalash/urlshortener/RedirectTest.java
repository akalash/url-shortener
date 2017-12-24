package com.github.akalash.urlshortener;

import com.github.akalash.urlshortener.domain.Account;
import com.github.akalash.urlshortener.repository.AccountRepository;
import com.github.akalash.urlshortener.services.CreateAccountService;
import com.github.akalash.urlshortener.services.RegisterUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for getRedirectInfo process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class RedirectTest extends AbstractIntegrationTest {

    @Autowired
    CreateAccountService createAccountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegisterUrlService registerUrlService;

    @Test
    public void should_redirectToOriginalUrl_because_everythingIsOk() throws Exception {
        String accountId = "testRedirect";
        String password = createAccountService.createAccount(accountId).getBody();
        Account account = accountRepository.findByUsername(accountId);

        String shortUrl = registerUrlService.registerUrl("google.ru", 301, account);

        mockMvc.perform(get("/" + shortUrl.replaceAll(".*/", ""))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isMovedPermanently())
                .andExpect(redirectedUrl("google.ru"));
    }
}