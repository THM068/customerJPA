package com.demo.customer.controllers;

import com.demo.customer.domain.Account;
import com.demo.customer.services.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by tm1c14 on 09/06/2015.
 */
@RestController
@RequestMapping(value="/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value="list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Account> list() {
        return accountRepository.findAll();
    }
}
