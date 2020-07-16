package com.bianyiit.server;

import com.bianyiit.domian.Account;
import com.bianyiit.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServer {
    @Autowired
    AccountMapper accountMapper;
    public Account findAccountById(Integer id){
        List<Account> accounts = accountMapper.selectAll();
        System.out.println(accounts);
        return accountMapper.selectByPrimaryKey(id);
    }

}
