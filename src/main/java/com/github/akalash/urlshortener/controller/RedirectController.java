package com.github.akalash.urlshortener.controller;

import com.github.akalash.urlshortener.domain.RedirectType;
import com.github.akalash.urlshortener.services.RedirectService;
import com.github.akalash.urlshortener.services.RedirectService.RedirectResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.http.HttpStatus.*;

/**
 * Redirect controller
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@RestController
public class RedirectController {

    private final RedirectService redirectService;

    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RedirectView redirect(@PathVariable String id) {
        RedirectResult redirect = redirectService.getRedirectInfo(id);
        if (redirect == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setStatusCode(NOT_FOUND);
            return redirectView;
        }
        RedirectView redirectView = new RedirectView(redirect.getUrl());
        redirectView.setStatusCode(redirect.getRedirectType() == RedirectType.MOVED_PERMANENTLY ? MOVED_PERMANENTLY : FOUND);
        return redirectView;
    }
}
