package com.xxd.learn.oauth2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        log.info("principal:[{}]", principal.toString());
        return principal;
    }
}
