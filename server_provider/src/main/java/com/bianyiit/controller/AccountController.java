package com.bianyiit.controller;

import com.bianyiit.domian.Account;
import com.bianyiit.server.AccountServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountServer accountServer;
    @GetMapping("{id}")
    public Account getUserById(@PathVariable("id") Integer id){
        if(id!=1){
            throw new RuntimeException();
        }
        return accountServer.findAccountById(id);
    }

}
