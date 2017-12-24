package com.github.akalash.urlshortener.controller;

import com.github.akalash.urlshortener.api.CreateAccountRequest;
import com.github.akalash.urlshortener.api.CreateAccountResponse;
import com.github.akalash.urlshortener.api.RegisterUrlRequest;
import com.github.akalash.urlshortener.api.RegisterUrlResponse;
import com.github.akalash.urlshortener.domain.Account;
import com.github.akalash.urlshortener.framework.Result;
import com.github.akalash.urlshortener.services.CreateAccountService;
import com.github.akalash.urlshortener.services.RegisterUrlService;
import com.github.akalash.urlshortener.services.StatisticService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.github.akalash.urlshortener.security.SecurityHelper.getCurrentAccount;

/**
 * Controller for managing registration url process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RegisterController {

    private final CreateAccountService createAccountService;
    private final RegisterUrlService registerUrlService;
    private final StatisticService statisticService;

    public RegisterController(CreateAccountService createAccountService,
                              RegisterUrlService registerUrlService,
                              StatisticService statisticService) {
        this.createAccountService = createAccountService;
        this.registerUrlService = registerUrlService;
        this.statisticService = statisticService;
    }

    @PostMapping(value = "/account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CreateAccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Result<String, CreateAccountService.Error> createAccountResult = createAccountService.createAccount(request.getAccountId());
        if (!createAccountResult.isSuccess()) {
            return CreateAccountResponse.error(createAccountResult.getError().getMessage());
        }

        return CreateAccountResponse.success(createAccountResult.getBody());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('REGISTRATOR')")
    public RegisterUrlResponse registerUrl(@Valid @RequestBody RegisterUrlRequest registerRequest) {
        String shortUrl = registerUrlService.registerUrl(
                registerRequest.getUrl(), registerRequest.getRedirectType(), getCurrentAccount()
        );
        return new RegisterUrlResponse(shortUrl);
    }

    @GetMapping("/statistic/{accountId}")
    @PreAuthorize("hasAuthority('REGISTRATOR')")
    public Map<String, Long> getStatistic(@PathVariable String accountId) {
        Account currentAccount = getCurrentAccount();
        if (!currentAccount.getUsername().equals(accountId)) {
            throw new InsufficientAuthenticationException("accountId and credentials does not match");
        }
        return statisticService.getStatistic(currentAccount);
    }
}
